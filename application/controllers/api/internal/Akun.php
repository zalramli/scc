<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Akun extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
    }

    function ambil_data_internal_post()
    {
        $id_internal = $this->post('id_internal');

        // variable array
        $result = array();
        $result['internal'] = array();

        $data_id = array(
            'id_internal' => $id_internal
        );

        // mengambil data dari database
        $query = $this->M_universal->get_data('internal', $data_id);
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'nama' => $row["nama"],
                    'no_hp' => $row["no_hp"],
                    'akun_line' => $row["akun_line"],
                    'username' => $row["username"],
                    'hak_akses' => $row["hak_akses"],
                    'jabatan_managerial' => $row["jabatan_managerial"],
                    'status_sj' => $row["status_sj"],
                    'foto' => $row["foto"]
                );

                array_push($result['internal'], $data);

                // membuat array untuk di transfer
                $result["success"] = "1";
                $result["message"] = "Berhasil Mengambil Data";
                $this->response($result, 200);
            }
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Data Tidak ditemukan Di dalam Server";
            $this->response($result, 200);
        }
    }

    function update_internal_post()
    {
        $id_internal = $this->post('id_internal');
        $nama = $this->post('nama');
        $no_hp = $this->post('no_hp');
        $akun_line = $this->post('akun_line');
        $username = $this->post('username');
        $password = $this->post('password');
        $hak_akses = $this->post('hak_akses');
        $jabatan_managerial = $this->post('jabatan_managerial');
        $status_sj = $this->post('status_sj');
        $foto = $this->post('foto');
        $nama_foto = $id_internal;

        $data = array();

        $where = array(
            'id_internal' => $id_internal
        );

        if (!empty($foto)) { // jika ada foto

            $cek_foto = "";

            // mengambil data dari database
            $query = $this->M_universal->get_data('internal', $where);
            foreach ($query->result_array() as $row) {

                $cek_foto = $row["foto"];
            }

            if ($cek_foto != "DEFIN") {
                // lokasi gambar berada
                $path = './upload/image/internal/';
                $format = '.jpg';
                unlink($path . $nama_foto . $format); // hapus data di folder dimana data tersimpan
            }

            $path2 = "./upload/image/internal/$nama_foto.jpg";
            file_put_contents($path2, base64_decode($foto));
        } else {
            $nama_foto = "DEFIN";
        }

        if (empty($password)) {
            $data = array(
                'id_internal'   => $id_internal,
                'nama'          => $nama,
                'no_hp'         => $no_hp,
                'akun_line'        => $akun_line,
                'username'      => $username,
                'foto'          => $nama_foto
            );
        } else {
            $data = array(
                'id_internal'  => $id_internal,
                'nama'          => $nama,
                'no_hp'         => $no_hp,
                'akun_line'     => $akun_line,
                'username'      => $username,
                'password'      => $password,
                'foto'          => $nama_foto
            );
        }

        $update =  $this->M_universal->update_data($where, 'internal', $data);
        if ($update) {

            // membuat array untuk di transfer ke API
            $result["success"] = "1";
            $result["message"] = "Berhasil Mengupdate Data";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Server Error !";
            $this->response(array($result, 200));
        }
    }
}
