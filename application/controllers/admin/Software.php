<?php
class Software extends CI_Controller
{
    function __construct()
    {
        parent::__construct();
        $this->load->model('admin/M_crud');
    }
    public function index()
    {
        $data['record'] = $this->M_crud->tampil_data('software')->result();
        $this->template->load('scc/template/admin', 'scc/konten/admin/software/tampil', $data);
    }
    public function store()
    {
        $data = array(
            'nama' => $this->input->post('nama')
        );
        $this->M_crud->input_data('software', $data);
        $this->session->set_flashdata('success', 'Ditambahkan');
        redirect('admin/software');
    }
    public function update()
    {
        $where = array(
            'id_software' => $this->input->post('id_software')
        );
        $data = array(
            'nama' => $this->input->post('nama')
        );
        $this->M_crud->update_data($where, 'software', $data);
        $this->session->set_flashdata('update', 'Diubah');
        redirect('admin/software');
    }
    public function delete($id)
    {
        $where = array('id_software' => $id);
        $this->M_crud->hapus_data($where, 'software');
        $this->session->set_flashdata('hapus', 'Dihapus');
        redirect('admin/software');
    }
}
