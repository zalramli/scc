<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Jadwal_bs extends CI_Controller {

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
        $where_senin = array(
        
            'hari' => 'Senin'
        );
        $where_selasa = array(
        
            'hari' => 'Selasa'
        );
        $where_rabu = array(
        
            'hari' => 'Rabu'
        );
        $where_kamis = array(
        
            'hari' => 'Kamis'
        );
        $where_jumat = array(
        
            'hari' => 'Jumat'
        );

        $data['record_senin'] = $this->M_crud->get_data('jadwal_bank_software',$where_senin)->result();
        $data['record_selasa'] = $this->M_crud->get_data('jadwal_bank_software',$where_selasa)->result();
        $data['record_rabu'] = $this->M_crud->get_data('jadwal_bank_software',$where_rabu)->result();
        $data['record_kamis'] = $this->M_crud->get_data('jadwal_bank_software',$where_kamis)->result();
        $data['record_jumat'] = $this->M_crud->get_data('jadwal_bank_software',$where_jumat)->result();

		$this->template->load('scc/template/admin', 'scc/konten/admin/jadwal_bs/tampil',$data);
    }

    public function store_jadwal()
    {
        // id internal ambil session
        // $id_internal = $this->input->post('id_internal');
        $hari = $this->input->post('hari');
        $jam_mulai = $this->input->post('jam_mulai');
        $menit_mulai = $this->input->post('menit_mulai');
        $jam_selesai = $this->input->post('jam_selesai');
        $menit_selesai = $this->input->post('menit_selesai');

        $mulai = $jam_mulai.':'.$menit_mulai;
        $selesai = $jam_selesai.':'.$menit_selesai;

        $data = array(
            'id_internal' => 1,
            'hari' => $hari,
            'jam_mulai' => $mulai,
            'jam_selesai' => $selesai,
            'status_booking' => 'Free',
            'status_aktif' => 'Aktif'
        );
        $this->M_crud->input_data('jadwal_bank_software',$data);
        $this->session->set_flashdata('success','Ditambahkan');
        redirect('admin/jadwal_bs');

    }

    function update_jadwal_senin()
    {
        $id = $this->input->post('id');
        $id_jadwal_bs = substr($id,0,1);
        $ubah_status = substr($id,2);

        $where = array(
            'id_jadwal_bs' => $id_jadwal_bs
        );
        $data = array(
            'status_aktif' => $ubah_status
        );

        $this->M_crud->update_data($where,'jadwal_bank_software',$data);
        echo $ubah_status;

    }

    function update_jadwal_selasa()
    {
        $id = $this->input->post('id');
        $id_jadwal_bs = substr($id,0,1);
        $ubah_status = substr($id,2);

        $where = array(
        'id_jadwal_bs' => $id_jadwal_bs
        );
        $data = array(
        'status_aktif' => $ubah_status
        );

        $this->M_crud->update_data($where,'jadwal_bank_software',$data);
        echo $ubah_status;

    }

    function update_jadwal_rabu()
    {
    $id = $this->input->post('id');
    $id_jadwal_bs = substr($id,0,1);
    $ubah_status = substr($id,2);

    $where = array(
    'id_jadwal_bs' => $id_jadwal_bs
    );
    $data = array(
    'status_aktif' => $ubah_status
    );

    $this->M_crud->update_data($where,'jadwal_bank_software',$data);
    echo $ubah_status;

    }

    function update_jadwal_kamis()
    {
        $id = $this->input->post('id');
        $id_jadwal_bs = substr($id,0,1);
        $ubah_status = substr($id,2);

        $where = array(
        'id_jadwal_bs' => $id_jadwal_bs
        );
        $data = array(
        'status_aktif' => $ubah_status
        );

        $this->M_crud->update_data($where,'jadwal_bank_software',$data);
        echo $ubah_status;

    }

    function update_jadwal_jumat()
    {
        $id = $this->input->post('id');
        $id_jadwal_bs = substr($id,0,1);
        $ubah_status = substr($id,2);

        $where = array(
        'id_jadwal_bs' => $id_jadwal_bs
        );
        $data = array(
        'status_aktif' => $ubah_status
        );

        $this->M_crud->update_data($where,'jadwal_bank_software',$data);
        echo $ubah_status;

    }

}
