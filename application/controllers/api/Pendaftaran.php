<?php
defined('BASEPATH') or exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

use Restserver\Libraries\REST_Controller;

class Pendaftaran extends REST_Controller
{

    function __construct($config = 'rest')
    {
        parent::__construct($config);
        $this->load->model("api/M_universal");
        $this->load->model("api/M_eksternal");
    }

    function eksternal_post()
    {
        // ambil data
        $id_eksternal = $this->M_eksternal->get_no_id_eksternal();
        $nama = $this->post('nama');
        $no_hp = $this->post('no_hp');
        $akun_line = $this->post('akun_line');
        $username = $this->post('username');
        $password = $this->post('password');
        $angkatan = $this->post('angkatan');
        $foto = $this->post('foto');

        $nama_foto = "DEFEK";

        $where = array(
            'username' => $username
        );

        $cek_username = $this->M_universal->get_data('eksternal', $where)->num_rows();

        if ($cek_username == 0) {

            if (!empty($foto)) {
                $nama_foto = $id_eksternal;
            }

            $data = array(
                'id_eksternal'  => $id_eksternal,
                'nama'          => $nama,
                'no_hp'         => $no_hp,
                'akun_line'     => $akun_line,
                'username'      => $username,
                'password'      => $password,
                'angkatan'      => $angkatan,
                'foto'          => $nama_foto
            );

            $insert =  $this->M_universal->input_data('eksternal', $data);
            if ($insert) {

                if (!empty($foto)) {
                    $path = "./upload/image/eksternal/$nama_foto.jpg";
                    file_put_contents($path, base64_decode($foto));
                }

                // membuat array untuk di transfer ke API
                $result["success"] = "1";
                $result["message"] = "Berhasil Melakukan Pendaftaran";
                $this->response($result, 200);
            } else {

                // membuat array untuk di transfer ke API
                $result["success"] = "0";
                $result["message"] = "Pendaftaran Gagal , Server Error !";
                $this->response($result, 200);
            }
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Pendaftaran Gagal , Username Sudah Terdaftar !";
            $this->response($result, 200);
        }
    }
}
