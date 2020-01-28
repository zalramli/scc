<?php
class M_internal extends CI_Model
{
    // autogenerate kode / ID
    function get_no_id_internal()
    {
        $field = "id_internal";
        $tabel = "internal";
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
            $kd = "IN001";
        }
        return $kd;
    }
}
