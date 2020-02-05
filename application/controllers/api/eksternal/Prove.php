<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Prove extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        $this->load->model("api/M_prove");
        date_default_timezone_set('Asia/Jakarta');
    }

    function tambah_prove_post()
    {
        // ambil data
        $id_prove =  $this->M_prove->get_kode_prove();
        $id_eksternal = $this->post('id_eksternal');
        $id_internal = $this->post('id_internal');
        $id_materi_prove = $this->post('id_materi_prove');
        $id_jadwal_prove = $this->post('id_jadwal_prove');
        $deskripsi_materi = $this->post('deskripsi_materi');
        $tanggal_prove = $this->post('tanggal_prove');

        $tanggal_booking =  date('Y-m-d');

        $kode_prove = $this->M_prove->get_kode_prove();
        $kata_sandi = $this->M_prove->get_kata_sandi(5);

        $data = array(
            'id_prove'   => $id_prove,
            'id_eksternal'   => $id_eksternal,
            'id_materi_prove'   => $id_materi_prove,
            'id_jadwal_prove'   => $id_jadwal_prove,
            'deskripsi_materi'   => $deskripsi_materi,
            'tanggal_booking'   => $tanggal_booking,
            'tanggal_prove'   => $tanggal_prove,
            'kode_prove'   => $kode_prove,
            'kata_sandi'   => $kata_sandi,
            'status_prove'   => "Belum Selesai"
        );

        $where_jadwal_prove = array(
            'id_jadwal_prove' => $id_jadwal_prove,
            'status_booking' => "Free"
        );

        // mengambil data dari database cek apakah free
        $query = $this->M_universal->get_data('jadwal_prove', $where_jadwal_prove);
        if ($query->num_rows() > 0) {

            // cek apakah ada list prove yang dia sudah buat dan belum selesai ?
            $where_id_eksternal = array(
                'id_eksternal' => $id_eksternal,
                'status_prove' => "Belum Selesai"
            );

            $query = $this->M_universal->get_data('list_prove', $where_id_eksternal);
            if ($query->num_rows() > 0) {

                // membuat array untuk di transfer ke API
                $result["success"] = "0";
                $result["message"] = "Prove Anda Sebelumnya Masih Belum Selesai !";
                $this->response($result, 200);
            } else {

                $insert =  $this->M_universal->input_data('prove', $data);
                if ($insert) {

                    // detail prove tambah
                    $data = array(
                        'id_prove'   => $id_prove,
                        'id_eksternal'   => $id_eksternal
                    );

                    $insert =  $this->M_universal->input_data('detail_prove', $data);

                    $where = array(
                        'id_jadwal_prove' =>  $id_jadwal_prove
                    );

                    $data_update = array(
                        'status_booking' => 'Unfree',
                        'terakhir_dibooking' => $tanggal_booking
                    );

                    $update = $this->M_universal->update_data($where, 'jadwal_prove', $data_update);

                    // membuat array untuk di transfer ke API
                    $result["success"] = "1";
                    $result["message"] = "Berhasil Membuat Prove";
                    $this->response($result, 200);
                } else {

                    // membuat array untuk di transfer ke API
                    $result["success"] = "0";
                    $result["message"] = "Coba Lagi, Server Error";
                    $this->response($result, 200);
                }
            }
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Jadwal Sudah dipesan ! , Coba jadwal Lainnya";
            $this->response($result, 200);
        }
    }

    function list_prove_post()
    {
        // mengambil data dari database
        $id = $this->post('id');
        $hak_akses = $this->post('hak_akses');

        $query = "";

        if ($hak_akses == "eksternal") {
            $where = array(
                'id_eksternal' => $id
            );
            $query = $this->M_universal->get_data('list_prove', $where);
        } else if ($hak_akses == "internal") {
            $where = array(
                'id_internal' => $id
            );
            $query = $this->M_universal->get_data('list_prove', $where);
        }

        // variable array
        $result = array();
        $result['list_prove'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                $tgl_prove = $row["tanggal_prove"];
                $id_prove = $row["id_prove"];
                $id_jadwal_prove = $row["id_jadwal_prove"];

                $status_prove = $row["status_prove"];

                $data = array();

                if ($tgl_prove < date('Y-m-d') && $status_prove == "Belum Selesai") {
                    $this->validasi_prove_gagal($id_prove, $id_jadwal_prove);
                } else {
                    // ambil detail data db
                    $data = array(
                        'id_prove' => $row["id_prove"],
                        'id_eksternal' => $row["id_eksternal"],
                        'nama_eksternal' => $row["nama_eksternal"],
                        'id_internal' => $row["id_internal"],
                        'nama_internal' => $row["nama_internal"],
                        'id_materi_prove' => $row["id_materi_prove"],
                        'nama_materi_prove' => $row["nama_materi_prove"],
                        'id_jadwal_prove' => $row["id_jadwal_prove"],
                        'hari' => $row["hari"],
                        'jam_mulai' => $row["jam_mulai"],
                        'jam_selesai' => $row["jam_selesai"],
                        'deskripsi_materi' => $row["deskripsi_materi"],
                        'tanggal_booking' => $row["tanggal_booking"],
                        'tanggal_prove' => $row["tanggal_prove"],
                        'kode_prove' => $row["kode_prove"],
                        'kata_sandi' => $row["kata_sandi"],
                        'status_prove' => $row["status_prove"]
                    );
                }



                array_push($result['list_prove'], $data);
            }

            // membuat array untuk di transfer
            $result["success"] = "1";
            $result["message"] = "Success Mengambil Data";
            $this->response($result, 200);
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Data List Prove Tidak Ditemukan";
            $this->response($result, 200);
        }
    }

    function list_prove_hanya_belum_selesai_post()
    {
        // mengambil data dari database
        $id = $this->post('id');
        $hak_akses = $this->post('hak_akses');

        $query = "";

        if ($hak_akses == "eksternal") {
            $where = array(
                'id_eksternal' => $id,
                'status_prove' => "Belum Selesai"
            );
            $query = $this->M_universal->get_data('list_prove', $where);
        } else if ($hak_akses == "internal") {
            $where = array(
                'id_internal' => $id,
                'status_prove' => "Belum Selesai"
            );
            $query = $this->M_universal->get_data('list_prove', $where);
        }

        // variable array
        $result = array();
        $result['list_prove'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_prove' => $row["id_prove"],
                    'id_eksternal' => $row["id_eksternal"],
                    'nama_eksternal' => $row["nama_eksternal"],
                    'id_internal' => $row["id_internal"],
                    'nama_internal' => $row["nama_internal"],
                    'id_materi_prove' => $row["id_materi_prove"],
                    'nama_materi_prove' => $row["nama_materi_prove"],
                    'id_jadwal_prove' => $row["id_jadwal_prove"],
                    'hari' => $row["hari"],
                    'jam_mulai' => $row["jam_mulai"],
                    'jam_selesai' => $row["jam_selesai"],
                    'deskripsi_materi' => $row["deskripsi_materi"],
                    'tanggal_booking' => $row["tanggal_booking"],
                    'tanggal_prove' => $row["tanggal_prove"],
                    'kode_prove' => $row["kode_prove"],
                    'kata_sandi' => $row["kata_sandi"],
                    'status_prove' => $row["status_prove"]
                );

                array_push($result['list_prove'], $data);
            }

            // membuat array untuk di transfer
            $result["success"] = "1";
            $result["message"] = "Success Mengambil Data";
            $this->response($result, 200);
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Data List Prove Tidak Ditemukan";
            $this->response($result, 200);
        }
    }

    function update_detail_prove_post()
    {
        $id_prove = $this->post('id_prove');
        $id_eksternal = $this->post('id_eksternal');
        $rating = $this->post('rating');
        $id_jadwal_prove = $this->post('id_jadwal_prove');

        $data = array(
            'rating'        => $rating
        );

        $where = array(
            'id_prove'      => $id_prove,
            'id_eksternal'  => $id_eksternal,
        );

        $update =  $this->M_universal->update_data($where, 'detail_prove', $data);

        if ($update) {

            // untuk mengubah prove status prove
            $data = array(
                'status_prove'        => "Selesai"
            );

            $where = array(
                'id_prove'      => $id_prove
            );

            $update =  $this->M_universal->update_data($where, 'prove', $data);

            if ($update) {

                // untuk mengubah jadwal status booking
                $where = array(
                    'id_jadwal_prove' =>  $id_jadwal_prove
                );

                $data_update = array(
                    'status_booking' => 'Free'
                );

                $update = $this->M_universal->update_data($where, 'jadwal_prove', $data_update);

                // membuat array untuk di transfer ke API
                $result["success"] = "1";
                $result["message"] = "Berhasil";
                $this->response($result, 200);
            } else {

                // membuat array untuk di transfer ke API
                $result["success"] = "0";
                $result["message"] = "Terjadi Kesalahan Server";
                $this->response($result, 200);
            }
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Terjadi Kesalahan Server";
            $this->response($result, 200);
        }
    }

    function delete_detail_prove_post()
    {
        $id_prove = $this->post('id_prove');
        $id_eksternal = $this->post('id_eksternal');
        $id_jadwal_prove = $this->post('id_jadwal_prove');

        $where = array(
            'id_prove' => $id_prove,
            'id_eksternal' => $id_eksternal
        );

        $hapus =  $this->M_universal->hapus_data($where, "detail_prove");

        if ($hapus) {

            // cek apakah ada anggota detail ?
            $where = array(
                'id_prove' => $id_prove
            );

            $query = $this->M_universal->get_data('detail_prove', $where);

            if ($query->num_rows() < 1) { // jika tidak ada

                // menghapus yang tidak ada detail
                $hapus =  $this->M_universal->hapus_data($where, "prove");

                // untuk mengubah jadwal status booking
                $where = array(
                    'id_jadwal_prove' =>  $id_jadwal_prove
                );

                $data_update = array(
                    'status_booking' => 'Free'
                );

                $update = $this->M_universal->update_data($where, 'jadwal_prove', $data_update);
            }

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Keluar Dari Pertemuan Prove";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Terjadi Kesalahan Server";
            $this->response($result, 200);
        }
    }

    function cek_kode_prove_post()
    {
        $kode_prove = $this->post('kode_prove');

        // variable array
        $result = array();
        $result['prove'] = array();

        // cek apakah ada kode_prove
        $where = array(
            'kode_prove' => $kode_prove,
            'status_prove' => "Belum Selesai"
        );

        $query = $this->M_universal->get_data('prove', $where);

        if ($query->num_rows() > 0) { // jika ada

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'kata_sandi' => $row["kata_sandi"]
                );

                array_push($result['prove'], $data);
            }

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Tidak Ditemukan Kode Prove : " . $kode_prove;
            $this->response($result, 200);
        }
    }

    function tambah_detail_prove_post()
    {
        $id_prove = $this->post('kode_prove');
        $id_eksternal = $this->post('id_eksternal');

        // variable array
        $result = array();
        $result['detail_prove'] = array();

        // cek apakah ada kode_prove
        $where = array(
            'id_prove' => $id_prove,
            'id_eksternal' => $id_eksternal
        );

        $query = $this->M_universal->get_data('detail_prove', $where);

        if ($query->num_rows() > 0) { // jika ada

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Anda Sudah Terdaftar Dalam Anggota Prove";
            $this->response($result, 200);
        } else {

            // detail prove tambah
            $data = array(
                'id_prove'   => $id_prove,
                'id_eksternal'   => $id_eksternal
            );

            $insert =  $this->M_universal->input_data('detail_prove', $data);

            if ($insert) {
                // membuat array untuk di transfer ke API
                $result["success"] = "1";
                $result["message"] = "Berhasil Masuk Prove";
                $this->response($result, 200);
            } else {
                // membuat array untuk di transfer ke API
                $result["success"] = "0";
                $result["message"] = "Terjadi Kesalahan Server";
                $this->response($result, 200);
            }
        }
    }

    function validasi_prove_gagal($id_prove, $id_jadwal_prove)
    {
        // untuk mengubah jadwal status prove
        $where = array(
            'id_prove' => $id_prove
        );

        $data_update = array(
            'status_prove' => 'Batal'
        );

        $update =  $this->M_universal->update_data($where, "prove", $data_update);

        // untuk mengubah jadwal status booking
        $where = array(
            'id_jadwal_prove' =>  $id_jadwal_prove
        );

        $data_update = array(
            'status_booking' => 'Free'
        );

        $update = $this->M_universal->update_data($where, 'jadwal_prove', $data_update);

        // membuat array untuk di transfer ke API
        $result["success"] = "1";
        $result["message"] = "Berhasil Validasi";
        $this->response($result, 200);
    }
}
