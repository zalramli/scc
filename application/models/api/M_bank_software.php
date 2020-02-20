<?php
class M_bank_software extends CI_Model
{
    // autogenerate kode
    function get_kode_bank_s()
    {
        date_default_timezone_set('Asia/Jakarta');
        $field = "kode_bank_s";
        $tabel = "bank_software";
        $digit = "3";
        $ymd = date('ymd');

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel WHERE SUBSTR($field, 1, 6) = $ymd LIMIT 1");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = sprintf('%0' . $digit . 's', $tmp);
            }
        } else {
            $kd = "001";
        }

        return "BS" . date('ymd') . '-' . $kd; // SELECT SUBSTR('191121-001', 1, 6); dari digit ke 1 sampai digit ke 6 
    }
}
