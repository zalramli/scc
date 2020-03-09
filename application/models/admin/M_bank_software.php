<?php
class M_bank_software extends CI_Model
{
    function tampil_bs()
    {
        $query = $this->db->query('SELECT kode_bank_s,eksternal.nama as nama_eksternal,angkatan,internal.nama as nama_internal,status_bs,tanggal_bs,rating FROM bank_software JOIN eksternal USING(id_eksternal) JOIN jadwal_bank_software USING(id_jadwal_bs) JOIN internal USING (id_internal) ORDER BY tanggal_bs DESC');
        return $query->result();
    }
    function detail_list_software($where)
    {
        return $this->db->select('*')->from('detail_bs')->join('software',
        'detail_bs.id_software=software.id_software')->where($where)->get()->result();
    }
    
}
