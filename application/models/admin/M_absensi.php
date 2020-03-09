<?php
class M_absensi extends CI_Model
{
    function tampil_absensi()
    {
        $query = $this->db->query('SELECT id_absensi,internal.nama as nama_internal,judul_absensi,tgl_absensi,jam_mulai,jam_selesai,status_absensi,kata_sandi FROM absensi JOIN internal USING(id_internal) ORDER BY tgl_absensi DESC');
        return $query->result();
    }
    function detail_list_absensi($where)
    {
        return $this->db->select('internal.nama as nama_internal,tgl_absen')->from('detail_absensi')->join('internal',
        'detail_absensi.id_internal=internal.id_internal')->where($where)->get()->result();
    }
    
}
