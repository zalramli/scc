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

            $insert =  $this->M_universal->input_data('prove', $data);
            if ($insert) {

                // detail 
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
                $this->response(array($result, 200));
            }
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Jadwal Sudah dipesan ! , Coba jadwal Lainnya";
            $this->response(array($result, 200));
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

    function ambil_data_prove_post()
    {
        $id_prove = $this->post('id_prove');

        // variable array
        $result = array();
        $result['prove'] = array();

        $data_id = array(
            'id_prove' => $id_prove
        );

        // mengambil data dari database
        $query = $this->M_universal->get_data('prove', $data_id);
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'nama' => $row["nama"],
                    'username' => $row["username"],
                    'alamat' => $row["alamat"],
                    'no_hp' => $row["no_hp"]
                );

                array_push($result['prove'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "success berhasil mengambil data";
                $this->response($result, 200);
            }
        } else {
            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "error data tidak ada";
            $this->response($result, 502);
        }
    }

    function update_prove_post()
    {
        $id_prove = $this->post('id_prove');
        $nama = $this->post('nama');
        $username = $this->post('username');
        $password = $this->post('password');
        $alamat = $this->post('alamat');
        $no_hp = $this->post('no_hp');

        $data = array();

        if (empty($password)) {
            $data = array(
                'id_prove' => $id_prove,
                'nama'          => $nama,
                'username'      => $username,
                'alamat'        => $alamat,
                'no_hp'         => $no_hp
            );
        } else {
            $data = array(
                'id_prove' => $id_prove,
                'nama'          => $nama,
                'username'      => $username,
                'password'      => password_hash($password, PASSWORD_DEFAULT),
                'alamat'        => $alamat,
                'no_hp'         => $no_hp
            );
        }

        $where = array(
            'id_prove' => $id_prove
        );

        $update =  $this->M_universal->update_data($where, 'prove', $data);
        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "success";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "error";
            $this->response(array($result, 502));
        }
    }

    function delete_prove_post()
    {
        $id_prove = $this->post('id');

        $where = array(
            'id_prove' => $id_prove
        );

        $hapus =  $this->M_universal->hapus_data($where, "prove");
        if ($hapus) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "success";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "error";
            $this->response(array($result, 502));
        }
    }
}