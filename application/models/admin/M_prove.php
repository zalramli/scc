<?php
class M_prove extends CI_Model
{
    function tampil_prove()
    {
        $this->db->select('id_prove,pilihan_materi,deskripsi_materi,status_prove,eksternal.nama AS nama_eksternal,internal.nama AS nama_internal,angkatan');
        $this->db->from('prove');
        $this->db->join('eksternal', 'eksternal.id_eksternal = prove.id_eksternal');
        $this->db->join('internal', 'internal.id_internal = prove.id_internal');
        return $query = $this->db->get()->result();
    }
    function detail_anggota_prove($where)
    {
        return $this->db->select('*')->from('detail_prove')->join('eksternal',
        'detail_prove.id_eksternal=eksternal.id_eksternal')->where($where)->get()->result();
    }
    
}
