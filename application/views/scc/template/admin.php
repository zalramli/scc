<!DOCTYPE html>
<html lang="en">

<head>

	<!-- Head -->
	<?php $this->load->view('scc/_partials/head.php'); ?>
	<!-- End of Head -->

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<?php $this->load->view('scc/_partials/sidebar.php'); ?>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<?php $this->load->view('scc/_partials/topbar.php'); ?>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<?php echo $contents; ?>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<?php $this->load->view('scc/_partials/footer.php'); ?>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top">
		<i class="fas fa-angle-up"></i>
	</a>

	<!-- Js -->
	<?php $this->load->view('scc/_partials/js.php'); ?>
	<!-- End of Js -->

</body>

</html>
