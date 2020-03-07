<script src="<?php echo base_url(); ?>_assets/safario/vendors/jquery/jquery-3.2.1.min.js"></script>
<script src="<?php echo base_url(); ?>_assets/safario/vendors/bootstrap/bootstrap.bundle.min.js"></script>
<script src="<?php echo base_url(); ?>_assets/safario/vendors/owl-carousel/owl.carousel.min.js"></script>
<script src="<?php echo base_url(); ?>_assets/safario/vendors/nice-select/jquery.nice-select.min.js"></script>
<script src="<?php echo base_url(); ?>_assets/safario/js/jquery.ajaxchimp.min.js"></script>
<script src="<?php echo base_url(); ?>_assets/safario/js/mail-script.js"></script>
<!-- <script src="<?php echo base_url(); ?>_assets/safario/js/skrollr.min.js"></script> -->
<script src="<?php echo base_url(); ?>_assets/safario/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<script type="text/javascript">
	// crud sukses 
	var pesan_sukses = $('.pesan-sukses').data(pesan_sukses);

	if (pesan_sukses) {
		Swal.fire(
			'Success',
			'Pendaftaran berhasil dilakukan',
			'success'
		)
	}
</script>
