<?php
class Sst extends CI_Controller
{
    function __construct()
    {
        parent::__construct();
        $this->load->model('admin/M_crud');
    }
    public function index()
    {
        $data['record'] = $this->M_crud->tampil_data('event_sst')->result();
        $this->template->load('scc/template/admin', 'scc/konten/admin/event_sst/tampil', $data);
    }
    public function store()
    {
        $data = array(
            'nama' => $this->input->post('nama'),
            'email' => $this->input->post('email'),
            'nama' => $this->input->post('nama'),
            'institusi' => $this->input->post('institusi'),
            'jurusan' => $this->input->post('jurusan'),
            'no_hp' => $this->input->post('no_hp'),
            'akun_line' => $this->input->post('akun_line'),
            'alasan' => $this->input->post('alasan')
        );
        $this->M_crud->input_data('event_sst', $data);
        $this->session->set_flashdata('success', 'Ditambahkan');
        redirect('sst');
    }
    // public function update()
    // {
    //     $where = array(
    //         'id_materi_prove' => $this->input->post('id_materi_prove')
    //     );
    //     $data = array(
    //         'nama' => $this->input->post('nama')
    //     );
    //     $this->M_crud->update_data($where, 'materi_prove', $data);
    //     $this->session->set_flashdata('update', 'Diubah');
    //     redirect('admin/materi_prove');
    // }
    public function delete($id)
    {
        $where = array('id_event_sst' => $id);
        $this->M_crud->hapus_data($where, 'event_sst');
        $this->session->set_flashdata('hapus', 'Dihapus');
        redirect('admin/sst');
    }
}
