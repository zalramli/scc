<?php
class Login extends CI_Controller
{
    function __construct()
    {
        parent::__construct();
        $this->load->model('admin/M_crud');
    }
    public function index()
    {
        $this->template->load('scc/template/login', 'scc/konten/admin/form_login');
    }
    public function store()
    {
        $userpass = $this->input->post('password');

        $cek = $this->M_crud->cek_login();
        if ($cek->num_rows() > 0) {
            foreach ($cek->result() as $row) {
                $id_internal = $row->id_internal;
                $username = $row->username;
                $nama = $row->nama;
                $foto = $row->foto;
                $password = $row->password;
            }
            if ($userpass == $password) {
                $data_session = array(
                    'id_internal' => $id_internal,
                    'username' => $username,
                    'nama' => $nama,
                    'foto' => $foto
                );
                $this->session->set_userdata($data_session);
                if ($row->nama == 'Hasri Wiji Aqsari')
                {
                    redirect('admin/prove');
                }
                else if ($row->nama == 'Ichwanul Kahfi Prasetya')
                {
                    redirect('admin/jadwal_bs');
                }
                else if ($row->nama == 'Eva Marella')
                {
                    redirect('admin/jadwal_prove');
                }
                else if ($row->nama == 'Yuniar Mega Kartikasari' || $row->nama == 'Sarnita Sadya')
                {
                    redirect('admin/sst');
                }
                else {
                    $this->session->set_flashdata('akses','Dilogin');
                    redirect('login');
                }
            }
            else {
                $this->session->set_flashdata('password','Dilogin');
                redirect('login');
            }
        } else {
            $this->session->set_flashdata('password','Dilogin');
            redirect('login');
        }
    }
    public function logout()
    {
        $this->session->sess_destroy();
        redirect(base_url('/login'));
    }

}
