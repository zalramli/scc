<?php
class Materi_prove extends CI_Controller
{
    function __construct()
    {
        parent::__construct();
        $this->load->model('admin/M_crud');
    }
    public function index()
    {
        $data['record'] = $this->M_crud->tampil_data('materi_prove')->result();
        $this->template->load('scc/template/admin', 'scc/konten/admin/materi_prove/tampil', $data);
    }
    public function store()
    {
        $data = array(
            'nama' => $this->input->post('nama')
        );
        $this->M_crud->input_data('materi_prove', $data);
        $this->session->set_flashdata('success', 'Ditambahkan');
        redirect('admin/materi_prove');
    }
    public function update()
    {
        $where = array(
            'id_materi_prove' => $this->input->post('id_materi_prove')
        );
        $data = array(
            'nama' => $this->input->post('nama')
        );
        $this->M_crud->update_data($where, 'materi_prove', $data);
        $this->session->set_flashdata('update', 'Diubah');
        redirect('admin/materi_prove');
    }
    public function delete($id)
    {
        $where = array('id_materi_prove' => $id);
        $this->M_crud->hapus_data($where, 'materi_prove');
        $this->session->set_flashdata('hapus', 'Dihapus');
        redirect('admin/materi_prove');
    }
}
