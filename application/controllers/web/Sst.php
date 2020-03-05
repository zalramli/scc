<?php
defined('BASEPATH') or exit('No direct script access allowed');

class Sst extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        $this->load->model('admin/M_crud');
    }

    public function index()
    {
        $this->template->load('scc/template/web', 'scc/konten/web/sst/tampil');
    }
}
