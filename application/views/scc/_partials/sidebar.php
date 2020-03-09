<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a class="sidebar-brand d-flex align-items-center justify-content-center" href="">
		<div class="sidebar-brand-icon rotate-n-15">
			<i class="fas fa-laugh-wink"></i>
		</div>
		<div class="sidebar-brand-text mx-3">SCC<sup></sup></div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">
		Sistem Informasi SCC
	</div>
	<!-- Nav Item - Tables -->
	<?php
			$eksternal = base_url('admin/eksternal');
			$internal = base_url('admin/internal');
			$prove = base_url('admin/prove');
			$jadwal_prove = base_url('admin/jadwal_prove');
			$jadwal_bs = base_url('admin/jadwal_bs');
			$materi_prove = base_url('admin/materi_prove');
			$software = base_url('admin/software');
			$sst = base_url('admin/sst');
			$bs = base_url('admin/bank_software');
			if ($this->session->userdata('nama') == 'Hasri Wiji Aqsari') {
				echo '
				<li class="nav-item">
				<a class="nav-link" href="' . $sst . '">
			<i class="fas fa-user"></i>
			<span>Pendaftaran SST</span></a>
			</li>
				<li class="nav-item">
				<a class="nav-link" href="' . $internal . '">
			<i class="fas fa-user"></i>
			<span>Anggota Internal</span></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="' . $eksternal . '">
			<i class="fas fa-user"></i>
			<span>Anggota Eksternal</span></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="' . $prove . '">
			<i class="fas fa-user"></i>
			<span>Prove</span></a>
			</li>
			<li class="nav-item">
					<a class="nav-link" href="' . $jadwal_prove . '">
			<i class="fas fa-user"></i>
			<span>Jadwal Prove</span></a>
			</li>
			<li class="nav-item">
			<a class="nav-link" href="'.$materi_prove.'">
			<i class="fas fa-user"></i>
			<span>Materi Prove</span></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="' . $bs . '">
			<i class="fas fa-user"></i>
			<span>Bank Software</span></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="' . $jadwal_bs . '">
			<i class="fas fa-user"></i>
			<span>Jadwal Bank Software</span></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="'.$software.'">
					<i class="fas fa-user"></i>
					<span>List Software</span></a>
			</li>
			
			';
			} else if ($this->session->userdata('nama') == 'Ichwanul Kahfi Prasetya') {
				echo '
				<li class="nav-item">
				<a class="nav-link" href="' . $bs . '">
			<i class="fas fa-user"></i>
			<span>Bank Software</span></a>
			</li>
				<li class="nav-item">
				<a class="nav-link" href="' . $jadwal_bs . '">
			<i class="fas fa-user"></i>
			<span>Jadwal Bank Software</span></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="'.$software.'">
					<i class="fas fa-user"></i>
					<span>List Software</span></a>
			</li>
			';
			} else if ($this->session->userdata('nama') == 'Eva Marella') {
				echo '
			<li class="nav-item">
			<a class="nav-link" href="' . $prove . '">
			<i class="fas fa-user"></i>
			<span>Prove</span></a>
			</li>
				<li class="nav-item">
					<a class="nav-link" href="' . $jadwal_prove . '">
			<i class="fas fa-user"></i>
			<span>Jadwal Prove</span></a>
			</li>
			<li class="nav-item">
			<a class="nav-link" href="'.$materi_prove.'">
			<i class="fas fa-user"></i>
			<span>Materi Prove</span></a>
			</li>
			';
			}
			else if ($this->session->userdata('nama') == 'Yuniar Mega Kartikasari' || $this->session->userdata('nama') == 'Sarnita Sadya') {
				echo '
				<li class="nav-item">
				<a class="nav-link" href="' . $sst . '">
			<i class="fas fa-user"></i>
			<span>Pendaftaran SST</span></a>
			</li>
			';
			}
			?>

	<!-- <li class="nav-item">
		<a class="nav-link" href="<?= base_url('admin/internal'); ?>">
			<i class="fas fa-user"></i>
			<span>Anggota Internal</span></a>
	</li>

	<li class="nav-item">
		<a class="nav-link" href="<?= base_url('admin/eksternal'); ?>">
			<i class="fas fa-user"></i>
			<span>Anggota Eksternal</span></a>
	</li>

	<li class="nav-item">
		<a class="nav-link" href="<?= base_url('admin/prove'); ?>">
			<i class="fas fa-user"></i>
			<span>Prove</span></a>
	</li>

	<li class="nav-item">
		<a class="nav-link" href="<?= base_url('admin/jadwal_prove'); ?>">
			<i class="fas fa-user"></i>
			<span>Jadwal Prove</span></a>
	</li>

	<li class="nav-item">
		<a class="nav-link" href="<?= base_url('admin/materi_prove'); ?>">
			<i class="fas fa-user"></i>
			<span>Materi Prove</span></a>
	</li>

	<li class="nav-item">
		<a class="nav-link" href="<?= base_url('admin/jadwal_bs'); ?>">
			<i class="fas fa-user"></i>
			<span>Jadwal Bank Software</span></a>
	</li>

	</li>
			<li class="nav-item">
			<a class="nav-link" href="'.$materi_prove.'">
			<i class="fas fa-user"></i>
			<span>Materi Prove</span></a>
	</li>
		
	<li class="nav-item">
		<a class="nav-link" href="<?= base_url('admin/software'); ?>">
			<i class="fas fa-user"></i>
			<span>List Software</span></a>
	</li> -->

	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>

</ul>