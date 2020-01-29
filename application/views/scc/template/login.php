<!DOCTYPE html>
<html lang="en">

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>SCC</title>
	<link rel="icon" href="<?= base_url() ?>_assets/sb_admin_2/img/logo-fix.png" type="image/x-icon">

	<!-- Custom fonts for this template-->
	<link href="<?= base_url() ?>_assets/sb_admin_2/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

	<!-- Custom styles for this template-->
	<link href="<?= base_url() ?>_assets/sb_admin_2/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<?php echo $contents; ?>

					</div>
				</div>

			</div>

		</div>

	</div>


	<!-- Bootstrap core JavaScript-->
	<script src="<?= base_url() ?>_assets/sb_admin_2/vendor/jquery/jquery.min.js"></script>
	<script src="<?= base_url() ?>_assets/sb_admin_2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="<?= base_url() ?>_assets/sb_admin_2/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<?= base_url() ?>_assets/sb_admin_2/js/sb-admin-2.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.nrp').mask('00000000000000');
			$('.hp').mask('000000000000000');
		})
	</script>
	<script>
		$('.karakter').keypress(function(e) {
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
		$('.karakterAngka').keypress(function(e) {
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
		var pesan_password = $('.pesan-password').data(pesan_password);
		var akses = $('.pesan-akses').data(akses);

		var cek_nrp = $('.cek-nrp').data(cek_nrp);
		var cek_upload = $('.cek-upload').data(cek_upload);
		if (pesan_password) {
			Swal.fire({
				icon: 'error',
				title: 'Gagal',
				text: 'Username atau password anda salah'
			})
		} else if (akses) {
			Swal.fire({
				icon: 'error',
				title: 'Gagal',
				text: 'Maaf anda tidak berhak mengkses'
			})
		} else if (cek_nrp) {
			Swal.fire({
				icon: 'error',
				title: 'Gagal',
				text: 'NRP sudah digunakan,silahkan hubungi CP dibawah'
			})
		} else if (cek_upload) {
			Swal.fire({
				icon: 'error',
				title: 'Gagal',
				text: 'Upload file harus ekstensi foto dan maks 5MB'
			})
		} else if (pesan_sukses) {
			Swal.fire(
				'Success',
				'Berhasil melakukan pendaftaran',
				'success'
			)
		}
	</script>

</body>

</html>