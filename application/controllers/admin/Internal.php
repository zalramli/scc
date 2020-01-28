<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Internal extends CI_Controller
{

    function __construct()
    {
        parent::__construct();
        // if (!$this->session->userdata('id_user')) {
        //     redirect('/login');
        // }
        $this->load->model('admin/M_crud');
        $this->load->model('admin/M_internal');
    }

    public function index()
    {
        $data['record'] = $this->M_crud->tampil_data('internal')->result();
        $this->template->load('scc/template/admin', 'scc/konten/admin/internal/tampil', $data);
    }

    public function store()
    {
        $id_internal = $this->M_internal->get_no_id_internal(); // get_no_id_internal()
        $nama = $this->input->post('nama');
        $no_hp = str_replace("-", "", $this->input->post('no_hp'));
        $akun_line = $this->input->post('akun_line');
        $username = $this->input->post('username');
        $password = $this->input->post('password');
        $hak_akses = $this->input->post('hak_akses');
        $jabatan_managerial = $this->input->post('jabatan_managerial');
        $status_sj = $this->input->post('status_sj');

        $data = array(
            'id_internal' => $id_internal,
            'nama' => $nama,
            'no_hp' => $no_hp,
            'akun_line' => $akun_line,
            'username' => $username,
            'password' => $password,
            'hak_akses' => $hak_akses,
            'jabatan_managerial' => $jabatan_managerial,
            'status_sj' => $status_sj
        );
        $this->M_crud->input_data('internal', $data);
        $this->session->set_flashdata('success', 'Ditambahkan');
        redirect('admin/internal');
    }

    public function update()
    {

        $id_internal = $this->input->post('id_internal');
        $nama = $this->input->post('nama');
        $no_hp = str_replace("-", "", $this->input->post('no_hp'));
        $akun_line = $this->input->post('akun_line');
        $username = $this->input->post('username');
        $password = $this->input->post('password');
        $hak_akses = $this->input->post('hak_akses');
        $jabatan_managerial = $this->input->post('jabatan_managerial');
        $status_sj = $this->input->post('status_sj');

        $where = array(
            'id_internal' => $this->input->post('id_internal')
        );

        $data = array(
            'nama' => $nama,
            'no_hp' => $no_hp,
            'akun_line' => $akun_line,
            'username' => $username,
            'password' => $password,
            'hak_akses' => $hak_akses,
            'jabatan_managerial' => $jabatan_managerial,
            'status_sj' => $status_sj
        );

        $this->M_crud->update_data($where, 'internal', $data);
        $this->session->set_flashdata('update', 'Diubah');
        redirect('admin/internal');
    }

    public function delete($id)
    {
        $where = array('id_internal' => $id);
        $this->M_crud->hapus_data($where, 'internal');
        $this->session->set_flashdata('hapus', 'Dihapus');
        redirect('admin/internal');
    }
}
