<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Jadwal_prove extends CI_Controller {

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
        $data['record'] = $this->M_crud->tampil_data('internal')->result();
		$this->template->load('scc/template/admin', 'scc/konten/admin/jadwal_prove/tampil',$data);
    }


    public function jadwal($id)
    {
        $where = array(
            'id_internal' => $id
        );
        $where_senin = array(
            'id_internal' => $id,
            'hari' => 'Senin'
        );
        $where_selasa = array(
            'id_internal' => $id,
            'hari' => 'Selasa'
        );
        $where_rabu = array(
            'id_internal' => $id,
            'hari' => 'Rabu'
        );
        $where_kamis = array(
            'id_internal' => $id,
            'hari' => 'Kamis'
        );
        $where_jumat = array(
            'id_internal' => $id,
            'hari' => 'Jumat'
        );

        $ambil_nama = $this->M_crud->get_data('internal',$where)->row();

        $data['nama'] = $ambil_nama->nama;
        $data['id_internal'] = $id;
        $data['record_senin'] = $this->M_crud->get_data('jadwal_prove',$where_senin)->result();
        $data['record_selasa'] = $this->M_crud->get_data('jadwal_prove',$where_selasa)->result();
        $data['record_rabu'] = $this->M_crud->get_data('jadwal_prove',$where_rabu)->result();
        $data['record_kamis'] = $this->M_crud->get_data('jadwal_prove',$where_kamis)->result();
        $data['record_jumat'] = $this->M_crud->get_data('jadwal_prove',$where_jumat)->result();

		$this->template->load('scc/template/admin', 'scc/konten/admin/jadwal_prove/jadwal',$data);
    }

    public function store_jadwal()
    {
        $id_internal = $this->input->post('id_internal');
        $hari = $this->input->post('hari');
        $jam_mulai = $this->input->post('jam_mulai');
        $menit_mulai = $this->input->post('menit_mulai');
        $jam_selesai = $this->input->post('jam_selesai');
        $menit_selesai = $this->input->post('menit_selesai');

        $mulai = $jam_mulai.':'.$menit_mulai;
        $selesai = $jam_selesai.':'.$menit_selesai;

        $data = array(
            'id_internal' => $id_internal,
            'hari' => $hari,
            'jam_mulai' => $mulai,
            'jam_selesai' => $selesai,
            'status_booking' => 'Free',
            'status_aktif' => 'Aktif'
        );
        $this->M_crud->input_data('jadwal_prove',$data);
        $this->session->set_flashdata('success','Ditambahkan');
        redirect('admin/jadwal_prove/jadwal/'.$id_internal);

    }
    function update_jadwal_senin()
    {
        $id_prove = $this->input->post('id');
        $ubah_status = $this->input->post('value');

        $where = array(
            'id_jadwal_prove' => $id_prove
        );
        $data = array(
            'status_aktif' => $ubah_status
        );

        $this->M_crud->update_data($where,'jadwal_prove',$data);
        echo $id_prove;

    }

    function update_jadwal_selasa()
    {
        $id_prove = $this->input->post('id');
        $ubah_status = $this->input->post('value');

        $where = array(
        'id_jadwal_prove' => $id_prove
        );
        $data = array(
        'status_aktif' => $ubah_status
        );

        $this->M_crud->update_data($where,'jadwal_prove',$data);
        echo $ubah_status;

    }
    function update_jadwal_rabu()
    {
        $id_prove = $this->input->post('id');
        $ubah_status = $this->input->post('value');

        $where = array(
            'id_jadwal_prove' => $id_prove
        );
        $data = array(
            'status_aktif' => $ubah_status
        );

        $this->M_crud->update_data($where,'jadwal_prove',$data);
        echo $ubah_status;
    }

    function update_jadwal_kamis()
    {
        $id_prove = $this->input->post('id');
        $ubah_status = $this->input->post('value');

        $where = array(
        'id_jadwal_prove' => $id_prove
        );
        $data = array(
        'status_aktif' => $ubah_status
        );

        $this->M_crud->update_data($where,'jadwal_prove',$data);
        echo $ubah_status;

    }

    function update_jadwal_jumat()
    {
        $id_prove = $this->input->post('id');
        $ubah_status = $this->input->post('value');

        $where = array(
        'id_jadwal_prove' => $id_prove
        );
        $data = array(
        'status_aktif' => $ubah_status
        );

        $this->M_crud->update_data($where,'jadwal_prove',$data);
        echo $ubah_status;

    }
}
