<?php if ($this->session->flashdata('success')) : ?>
	<div class="pesan-sukses" data-flashdata="<?= $this->session->flashdata('success'); ?>"></div>
<?php endif; ?>
<section class="hero-banner magic-ball">
    <div class="container">

    <div class="row align-items-center text-center text-md-left">
        <div class="col-md-6 col-lg-5 mb-5 mb-md-0">
        <h1>Statistics Software Training</h1>
        <p>
        SST(Statistics Software Training) adalah pelatihan yang diadakan oleh Statistika ITS untuk mahasiswa se-Surabaya supaya lebih memahami dalam penggunaan aplikasi Statistika. Tujuan diadakannya SST adalah membantu peserta mengetahui cara penggunaan Python dan memahami dasar-dasar Machine Learning.
        </p>  
        </div>
        <div class="col-md-6 col-lg-7 col-xl-6 offset-xl-1">
        <img class="img-fluid" src="<?php echo base_url();?>_assets/safario/img/home/hero-img-sst.png" alt="">
        </div>
    </div>
    </div>
</section>
<section class="section-margin">
    <div class="container">
    <div class="section-intro text-center pb-90px">
        <h2>Pendaftaran SST</h2>
    </div>
      <div class="row">
        <div class="col-lg-8">
          <form action="<?php echo base_url('admin/sst/store') ?>" class="form-contact contact_form" method="post">
            <div class="row">
            <div class="col-sm-12">
              <h5>* Biaya pendaftaran sebesar 150rb</h5>
              <h6>* Pembayaran dilakukan melalui nomor VA 8257715010710001. Kirimkan bukti pembayaran ke CP.</h6>
              <br>
            </div>
            <div class="col-sm-6">
                <div class="form-group">
                  <input class="form-control" name="nama" id="name" type="text" placeholder="Masukan nama" required>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="form-group">
                  <input class="form-control" name="email" id="email" type="email" placeholder="Masukan email" required>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="form-group">
                  <input class="form-control" name="institusi" id="name" type="text" placeholder="Masukan universitas/institusi" required>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="form-group">
                <input class="form-control" name="jurusan" id="name" type="text" placeholder="Masukan jurusan" required>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="form-group">
                  <input class="form-control" name="no_hp" id="name" type="text" placeholder="Masukan no. hp" required>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="form-group">
                  <input class="form-control" name="akun_line" id="name" type="text" placeholder="Masukan ID Line" required>
                </div>
              </div>
              <div class="col-12">
                <div class="form-group">
                  <textarea class="form-control w-100" name="alasan" id="message" cols="30" rows="9"
                    placeholder="Alasan mengikuti SST" required></textarea>
                </div>
              </div>
              <p>*</p>
            </div>
            <div class="form-group mt-3">
              <button type="submit" class="button button-contactForm">Simpan</button>
            </div>
          </form>


        </div>

        <div class="col-lg-4">
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-home"></i></span>
            <div class="media-body">
              <h3>Departemen Statistika FSAD ITS</h3>
              <p>Jl. Teknik Mesin No.175, Keputih, Kec. Sukolilo, Kota SBY, Jawa Timur 60115</p>
            </div>
          </div>
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-email"></i></span>
            <div class="media-body">
              <h3>scc.himasta@gmail.com</h3>
            </div>
          </div>
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="fab fa-line"></i></span>
            <div class="media-body">
              <h3>@yuniar_yun</h3>
            </div>
          </div>
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-tablet"></i></span>
            <div class="media-body">
              <h3><a href="tel:454545654">0819-0521-7438</a></h3>
              <p>Yuniar Mega Kartikasari</p>
            </div>
          </div>
          
        </div>
      </div>
    </div>
  </section>