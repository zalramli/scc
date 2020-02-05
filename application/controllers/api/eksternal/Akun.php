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
        date_default_timezone_set('Asia/Jakarta');
    }

    function ambil_data_eksternal_post()
    {
        $id_eksternal = $this->post('id_eksternal');

        // variable array
        $result = array();
        $result['eksternal'] = array();

        $data_id = array(
            'id_eksternal' => $id_eksternal
        );

        // mengambil data dari database
        $query = $this->M_universal->get_data('eksternal', $data_id);
        if ($query->num_rows() > 0) {

            // mengeluarkan data dari database
            foreach ($query->result_array() as $row) {

                // ambil detail data db
                $data = array(
                    'nama' => $row["nama"],
                    'no_hp' => $row["no_hp"],
                    'akun_line' => $row["akun_line"],
                    'username' => $row["username"],
                    'angkatan' => $row["angkatan"],
                    'foto' => $row["foto"]
                );

                array_push($result['eksternal'], $data);
            }

            // membuat array untuk di transfer
            $result["success"] = "1";
            $result["message"] = "Berhasil Mengambil Data";
            $this->response($result, 200);
        } else {

            // membuat array untuk di transfer ke API
            $result["success"] = "0";
            $result["message"] = "Data Tidak ditemukan Di dalam Server";
            $this->response($result, 200);
        }
    }

    function update_eksternal_post()
    {
        $id_eksternal = $this->post('id_eksternal');
        $nama = $this->post('nama');
        $no_hp = $this->post('no_hp');
        $akun_line = $this->post('akun_line');
        $username = $this->post('username');
        $password = $this->post('password');
        $angkatan = $this->post('angkatan');
        $foto = $this->post('foto');
        $nama_foto = $id_eksternal;

        $data = array();

        if (empty($password)) {
            $data = array(
                'id_eksternal'   => $id_eksternal,
                'nama'          => $nama,
                'no_hp'         => $no_hp,
                'akun_line'        => $akun_line,
                'username'      => $username,
                'angkatan'      => $angkatan,
                'foto'          => $nama_foto
            );
        } else {
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
        }

        $where = array(
            'id_eksternal' => $id_eksternal
        );

        if (!empty($foto)) { // jika ada foto

            $cek_foto = "";

            // mengambil data dari database
            $query = $this->M_universal->get_data('eksternal', $where);
            foreach ($query->result_array() as $row) {

                $cek_foto = $row["foto"];
            }

            if ($cek_foto != "DEFEK") {
                // lokasi gambar berada
                $path = './upload/image/eksternal/';
                $format = '.jpg';
                unlink($path . $nama_foto . $format); // hapus data di folder dimana data tersimpan
            }

            $path2 = "./upload/image/eksternal/$nama_foto.jpg";
            file_put_contents($path2, base64_decode($foto));
        }

        $update =  $this->M_universal->update_data($where, 'eksternal', $data);
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
