<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Prove extends CI_Controller {

    function __construct()
    {
        parent::__construct();
        // if (!$this->session->userdata('id_user')) {
        //     redirect('/login');
        // }
        $this->load->model('admin/M_crud');
        $this->load->model('admin/M_prove');
    }

	public function index()
	{
        $data['record'] = $this->M_prove->tampil_prove();
		$this->template->load('scc/template/admin', 'scc/konten/admin/prove/tampil',$data);
    }

    function detail_anggota_prove()
    {
        $id_prove = $this->input->post('id_prove');
        $where = array(
            'id_prove' => $id_prove
        );
        $data_tbl['tbl_data'] = $this->M_prove->detail_anggota_prove($where);
        $data = json_encode($data_tbl);
        echo $data;
    }

    public function delete($id)
    {
        $where = array('id_prove' => $id);
        $this->M_crud->hapus_data($where, 'prove');
        $this->M_crud->hapus_data($where, 'detail_prove');
        $this->session->set_flashdata('hapus', 'Dihapus');
        redirect('admin/materi_prove');
    }

    // public function delete($id)
    // {
    //     $where = array('id_eksternal' => $id);
    //     $this->M_crud->hapus_data($where, 'eksternal');
    //     $this->session->set_flashdata('hapus','Dihapus');
    //     redirect('admin/eksternal');
    // }
}
