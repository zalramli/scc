<header class="header_area">
    <div class="main_menu">
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container box_1620">
        <a class="navbar-brand logo_h" href="index.html"><img src="<?php echo base_url() ?>_assets/safario/img/logo.png" alt=""></a><h4 class="mt-3">Statistics Computer Course</h4>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
            <ul class="nav navbar-nav menu_nav justify-content-end">
            <li class="nav-item <?php if($this->uri->segment('1') == "" || $this->uri->segment('1') == "home"){echo "active";} ?>"><a class="nav-link" href="<?php echo base_url('home'); ?>">Home</a></li>
            <li class="nav-item <?php if($this->uri->segment('1') == "blog"){echo "active";} ?>"><a class="nav-link" href="<?php echo base_url('blog'); ?>">Blog</a></li>
            <li class="nav-item <?php if($this->uri->segment('1') == "tutorial"){echo "active";} ?>"><a class="nav-link" href="<?php echo base_url('tutorial'); ?>">Tutorial</a>
            <li class="nav-item <?php if($this->uri->segment('1') == "gallery"){echo "active";} ?>"><a class="nav-link" href="<?php echo base_url('gallery'); ?>">Gallery</a>
            <li class="nav-item <?php if($this->uri->segment('1') == "struktur"){echo "active";} ?>"><a class="nav-link" href="<?php echo base_url('struktur'); ?>">Struktur</a>
            <li class="nav-item <?php if($this->uri->segment('1') == "contact"){echo "active";} ?>"><a class="nav-link" href="<?php echo base_url('contact'); ?>">Contact</a></li>
            </ul>
        </div>
        </div>
    </nav>
    </div>
</header>