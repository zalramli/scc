<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Bank_software extends CI_Controller {

    function __construct()
    {
        parent::__construct();
        // if (!$this->session->userdata('id_user')) {
        //     redirect('/login');
        // }
        $this->load->model('admin/M_crud');
        $this->load->model('admin/M_bank_software');
    }

	public function index()
	{
        $data['record'] = $this->M_bank_software->tampil_bs();
		$this->template->load('scc/template/admin', 'scc/konten/admin/bank_software/tampil',$data);
    }

    public function detail_list_software()
    {
        $kode_bank_s = $this->input->post('kode_bank_s');
        $where = array(
            'kode_bank_s' => $kode_bank_s
        );
        $data_tbl['tbl_data'] = $this->M_bank_software->detail_list_software($where);
        $data = json_encode($data_tbl);
        echo $data;
    }

    public function delete($id)
    {
        $where = array('kode_bank_s' => $id);
        $this->M_crud->hapus_data($where, 'bank_software');
        $this->M_crud->hapus_data($where, 'detail_bs');
        $this->session->set_flashdata('hapus', 'Dihapus');
        redirect('admin/bank_software');
    }

    // public function delete($id)
    // {
    //     $where = array('id_eksternal' => $id);
    //     $this->M_crud->hapus_data($where, 'eksternal');
    //     $this->session->set_flashdata('hapus','Dihapus');
    //     redirect('admin/eksternal');
    // }
}
