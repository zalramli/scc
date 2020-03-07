<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Tutorial extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        $this->load->model('admin/M_crud');
    }

    public function index()
    {
        $this->db->from("tutorial");
        $this->db->order_by("id_tutorial", "desc");
        $data['record'] = $this->db->get()->result(); 
        $this->template->load('scc/template/web', 'scc/konten/web/tutorial/tampil',$data);
    }
}
