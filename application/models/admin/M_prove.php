<?php
class M_prove extends CI_Model
{
    function tampil_prove()
    {
        $query = $this->db->query('SELECT id_prove,eksternal.nama as nama_eksternal,angkatan,internal.nama as nama_internal,materi_prove.nama as nama_materi,deskripsi_materi,status_prove,tanggal_prove FROM prove JOIN eksternal USING(id_eksternal) JOIN materi_prove USING(id_materi_prove) JOIN jadwal_prove USING(id_jadwal_prove) JOIN internal USING (id_internal) ORDER BY tanggal_prove DESC');
        return $query->result();
    }
    function detail_anggota_prove($where)
    {
        return $this->db->select('*')->from('detail_prove')->join('eksternal',
        'detail_prove.id_eksternal=eksternal.id_eksternal')->where($where)->get()->result();
    }
    
}
