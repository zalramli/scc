<?php
class M_absensi extends CI_Model
{
    // autogenerate kode
    function get_id_absensi()
    {
        date_default_timezone_set('Asia/Jakarta');
        $field = "id_absensi";
        $tabel = "absensi";
        $digit = "3";
        $ymd = date('ymd');

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel WHERE SUBSTR($field, 3, 8) = $ymd LIMIT 1");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = sprintf('%0' . $digit . 's', $tmp);
            }
        } else {
            $kd = "001";
        }

        return "AB" . date('ymd') . '-' . $kd; // SELECT SUBSTR('AB200221-001', 3, 8); dari digit ke 3 sampai digit ke 8 
    }

    function get_kata_sandi($length = 5)
    {
        $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        $charactersLength = strlen($characters);
        $randomString = '';
        for ($i = 0; $i < $length; $i++) {
            $randomString .= $characters[rand(0, $charactersLength - 1)];
        }
        return $randomString;
    }
}
