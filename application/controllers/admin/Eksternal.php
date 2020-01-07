<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Eksternal extends CI_Controller {

    function __construct()
    {
        parent::__construct();
        // if (!$this->session->userdata('id_user')) {
        //     redirect('/login');
        // }
        $this->load->model('admin/M_crud');
    }

	public function index()
	{
        $data['record'] = $this->M_crud->tampil_data('eksternal')->result();
		$this->template->load('scc/template/admin', 'scc/konten/admin/eksternal/tampil',$data);
    }

    public function delete($id)
    {
        $where = array('id_eksternal' => $id);
        $this->M_crud->hapus_data($where, 'eksternal');
        $this->session->set_flashdata('hapus','Dihapus');
        redirect('admin/eksternal');
    }
}
