<?php
class M_crud extends CI_Model
{
    function tampil_data($table)
    {
        return $this->db->get($table);
    }

    function input_data($table, $data)
    {
        $status = $this->db->insert($table, $data);
        return $status;
    }

    function hapus_data($where, $table)
    {
        $this->db->where($where);
        $status = $this->db->delete($table);
        return $status;
    }

    function get_data($table, $where)
    {
        return $this->db->get_where($table, $where);
    }

    function update_data($where, $table, $data)
    {
        $this->db->where($where);
        $status = $this->db->update($table, $data);
        return $status;
    }

    public function cek_login()
    {
        $where = array(
            'username' => $this->input->post('username')
        );
        $this->db->select('*'); // Select fields
        $this->db->from('internal'); // from Table1
        $this->db->where($where);
        return $this->db->get();
    }
    
}
