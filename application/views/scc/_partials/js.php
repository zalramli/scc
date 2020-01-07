<!-- Bootstrap core JavaScript-->
<script src="<?= base_url(); ?>assets/sb_admin_2/vendor/jquery/jquery.min.js"></script>
<script src="<?= base_url(); ?>assets/sb_admin_2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="<?= base_url(); ?>assets/sb_admin_2/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="<?= base_url(); ?>assets/sb_admin_2/js/sb-admin-2.min.js"></script>
<script src="<?= base_url(); ?>assets/sb_admin_2/vendor/datatables/jquery.dataTables.min.js"> </script>
<script src="<?= base_url(); ?>assets/sb_admin_2/vendor/datatables/dataTables.bootstrap4.min.js"></script>
<script src="<?= base_url(); ?>assets/sb_admin_2/js/demo/datatables-demo.js"></script>
<!-- Agar input tidak ada history -->
<script>
	$("form :input").attr("autocomplete", "off");

</script>
<!-- Format Rupiah -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.7/js/select2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.11.0/baguetteBox.js"></script>

<!-- validasi inputan rupiah -->
<script type="text/javascript">
	$(document).ready(function () {
		$('.rupiah').mask('000.000.000', {
			reverse: true
		});
		$('.no_hp').mask('0000-0000-0000-000');
	})

</script>
<!-- Validasi hanya karakter -->
<script>
	$('.karakter').keypress(function (e) {
		var regex = new RegExp(/^[a-zA-Z\s]+$/);
		var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		if (regex.test(str)) {
			return true;
		} else {
			e.preventDefault();
			return false;
		}
	});

</script>
<script>
	$('.karakterAngka').keypress(function (e) {
		var regex = new RegExp(/^[a-z0-9\s]+$/i);
		var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		if (regex.test(str)) {
			return true;
		} else {
			e.preventDefault();
			return false;
		}
	});

</script>
<script type="text/javascript">
	// crud sukses 
	var pesan_sukses = $('.pesan-sukses').data(pesan_sukses);
	var pesan_update = $('.pesan-update').data(pesan_update);
	var pesan_hapus = $('.pesan-hapus').data(pesan_hapus);
	if (pesan_sukses) {
		Swal.fire(
			'Success',
			'Data Berhasil Ditambahkan',
			'success'
		)
	} else if (pesan_update) {
		Swal.fire(
			'Success',
			'Data Berhasil Diubah',
			'success'
		)
	} else if (pesan_hapus) {
		Swal.fire(
			'Success',
			'Data Berhasil Dihapus',
			'success'
		)
	}

</script>
<script>
	$('#dataTable').DataTable({
		ordering: false
	});

</script>
