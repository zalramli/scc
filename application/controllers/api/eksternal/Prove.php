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
            'id_eksternal'   => $id_eksternal,
            'id_internal'   => $id_internal,
            'id_materi_prove'   => $id_materi_prove,
            'id_jadwal_prove'   => $id_jadwal_prove,
            'deskripsi_materi'   => $deskripsi_materi,
            'tanggal_booking'   => $tanggal_booking,
            'tanggal_prove'   => $tanggal_prove,
            'kode_prove'   => $kode_prove,
            'kata_sandi'   => $kata_sandi,
            'status_prove'   => "Belum Selesai",
        );

        $insert =  $this->M_universal->input_data('prove', $data);
        if ($insert) {

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
    }

    function list_prove_get()
    {
        // mengambil data dari database
        $query = $this->M_universal->tampil_data('prove');

        // variable array
        $result = array();
        $result['prove'] = array();

        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'id_prove' => $row["id_prove"],
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
