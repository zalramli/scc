<?php
class M_eksternal extends CI_Model
{
    // autogenerate kode / ID
    function get_no_id_eksternal()
    {
        $field = "id_eksternal";
        $tabel = "eksternal";
        $digit = "3";
        $kode = "EK";

        $q = $this->db->query("SELECT MAX(RIGHT($field,$digit)) AS kd_max FROM $tabel");
        $kd = "";
        if ($q->num_rows() > 0) {
            foreach ($q->result() as $k) {
                $tmp = ((int) $k->kd_max) + 1;
                $kd = $kode . sprintf('%0' . $digit . 's',  $tmp);
            }
        } else {
            $kd = "EK001";
        }
        return $kd;
    }
}
