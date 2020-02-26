
<!DOCTYPE html>
<html lang="en">

<?php $this->load->view('scc/_partials_web/head.php'); ?>

<body class="bg-shape">

<!--================ Header Menu Area start =================-->
<?php $this->load->view('scc/_partials_web/nav.php'); ?>

<!--================Header Menu Area =================-->

<?php echo $contents; ?>


<!-- ================ start footer Area ================= -->
<?php $this->load->view('scc/_partials_web/footer.php'); ?>
<!-- ================ End footer Area ================= -->

<?php $this->load->view('scc/_partials_web/js.php'); ?>

</body>

</html>