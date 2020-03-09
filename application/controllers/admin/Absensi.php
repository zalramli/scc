<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Absensi extends CI_Controller {

    function __construct()
    {
        parent::__construct();
        // if (!$this->session->userdata('id_user')) {
        //     redirect('/login');
        // }
        $this->load->model('admin/M_crud');
        $this->load->model('admin/M_absensi');
    }

	public function index()
	{
        $data['record'] = $this->M_absensi->tampil_absensi();
		$this->template->load('scc/template/admin', 'scc/konten/admin/absensi/tampil',$data);
    }

    public function detail_list_absensi()
    {
        $id_absensi = $this->input->post('id_absensi');
        $where = array(
            'id_absensi' => $id_absensi
        );
        $data_tbl['tbl_data'] = $this->M_absensi->detail_list_absensi($where);
        $data = json_encode($data_tbl);
        echo $data;
    }

    public function delete($id)
    {
        $where = array('id_absensi' => $id);
        $this->M_crud->hapus_data($where, 'absensi');
        $this->M_crud->hapus_data($where, 'detail_absensi');
        $this->session->set_flashdata('hapus', 'Dihapus');
        redirect('admin/absensi');
    }

    // public function delete($id)
    // {
    //     $where = array('id_eksternal' => $id);
    //     $this->M_crud->hapus_data($where, 'eksternal');
    //     $this->session->set_flashdata('hapus','Dihapus');
    //     redirect('admin/eksternal');
    // }
}
