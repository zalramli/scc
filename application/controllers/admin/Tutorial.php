<?php
class Tutorial extends CI_Controller
{
    function __construct()
    {
        parent::__construct();
        $this->load->model('admin/M_crud');
        date_default_timezone_set('Asia/Jakarta');
        $this->load->library('upload');
    }
    public function index()
    {
        $data['record'] = $this->M_crud->tampil_data('tutorial')->result();
        $this->template->load('scc/template/admin', 'scc/konten/admin/tutorial/tampil', $data);
    }
    public function store()
    {
        $upload['upload_path'] ='_assets/image_web/';
        $upload['allowed_types'] ='jpg|png|jpeg';
        $upload['file_name'] = $this->input->post('judul');
        $upload['overwrite'] =true;
        $upload['max_size'] =3072;
        $this->upload->initialize($upload);
        $this->upload->do_upload('foto'); // input name which have to upload
        $cv_upload = $this->upload->data(); //variable which store the path

        
        if ($this->upload->do_upload('foto') == FALSE){
            $this->session->set_flashdata('upload','Diupload');
            redirect('admin/tutorial');
        }
        
        else
        {
            $data = array(
                'judul' => $this->input->post('judul'),
                'foto' => $cv_upload['file_name'],
                'deskripsi' => $this->input->post('deskripsi'),
                'embed' => $this->input->post('embed'),
                'tanggal_upload' => date('Y-m-d')
            );
            $this->M_crud->input_data('tutorial', $data);
            $this->session->set_flashdata('success', 'Ditambahkan');
            redirect('admin/tutorial');
        }
    }
    public function update()
    {
        $where = array(
            'id_tutorial' => $this->input->post('id_tutorial')
        );
        
        $upload['upload_path'] ='_assets/image_web/';
        $upload['allowed_types'] ='jpg|png|jpeg';
        $upload['file_name'] = $this->input->post('judul');
        $upload['overwrite'] =true;
        $upload['max_size'] =3072;
        $this->upload->initialize($upload);
        $this->upload->do_upload('foto'); // input name which have to upload
        $cv_upload = $this->upload->data(); //variable which store the path            
        

        if($this->upload->do_upload('foto') == TRUE)
        {
            
            $cek = $this->db->get_where('tutorial', $where)->result();
            $judul_foto = "";
            foreach($cek as $ambil)
            {
                $judul_foto = $ambil->foto;
            }
            // Hapus foto lama
            delete_files('_assets/image_web/'.$judul_foto, TRUE);
            
            $data = array(
                'judul' => $this->input->post('judul'),
                'foto' => $cv_upload['file_name'],
                'deskripsi' => $this->input->post('deskripsi'),
                'embed' => $this->input->post('embed')
            );
            
        }
        else
        {
            $data = array(
                'judul' => $this->input->post('judul'),
                'deskripsi' => $this->input->post('deskripsi'),
                'embed' => $this->input->post('embed')
            );
        }
        $this->M_crud->update_data($where, 'tutorial', $data);
        $this->session->set_flashdata('update', 'Diubah');
        redirect('admin/tutorial');
        
        
    }
    public function delete($id)
    {
        $where = array('id_tutorial' => $id);
        $this->M_crud->hapus_data($where, 'tutorial');
        $this->session->set_flashdata('hapus', 'Dihapus');
        redirect('admin/tutorial');
    }
}
