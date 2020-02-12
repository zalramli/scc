<!--Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->

<!DOCTYPE html>
<html lang="zxx">

<head>
    <?php $this->load->view('scc/_partials_web/head.php'); ?>
    <!-- //online-fonts -->
</head>

<body>
    <?php $this->load->view('scc/_partials_web/nav.php'); ?>

    <!-- //nav -->
    <!-- banner -->

    <div class="main position-relative">
        <!-- logo -->
        <!-- //logo -->
        <!-- banner slider -->
        <?php
        if ($this->uri->segment(1) == "" || $this->uri->segment(1) == "home") {
        ?>
            <section class="slide-wrapper">
                <div id="myCarousel" class="carousel  slide" data-ride="carousel" data-interval="10000">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                        <li data-target="#myCarousel" data-slide-to="3"></li>
                    </ol>
                    <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <!-- carousel item -->
                            <!-- banner slide -->
                            <div class="agile_banner bg1 text-center">
                                <div class="layer">
                                    <div class="container">
                                        <div class="banner_text_wthree">
                                            <h4 class="my-3">Distant Corners of the World</h4>
                                            <ul class="list-inline bnr_list_w3 mt-sm-5 mt-3">
                                                <li class="list-inline-item">
                                                    <a class="btn  text-white text-uppercase" href="<?php echo base_url('contact') ?>">contact us</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- //banner slide -->
                        </div>
                        <!-- //carousel item -->
                        <!-- carousel item -->
                        <div class="carousel-item">
                            <!-- banner slide -->
                            <div class="agile_banner bg2 text-center">
                                <div class="layer">
                                    <div class="container">
                                        <div class="banner_text_wthree">
                                            <h4 class="my-3">Experience The Best Trip Ever</h4>
                                            <ul class="list-inline bnr_list_w3 mt-sm-5 mt-3">
                                                <li class="list-inline-item">
                                                    <a class="btn  text-white text-uppercase" href="<?php echo base_url('contact') ?>">contact us</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- //banner slide -->
                        </div>
                        <!-- //carousel item -->
                        <!-- carousel item -->
                        <div class="carousel-item">
                            <!-- banner slide -->
                            <div class="agile_banner bg3 text-center">
                                <div class="layer">
                                    <div class="container">
                                        <div class="banner_text_wthree">
                                            <h4 class="my-3">DICOVER WORLD WITH US</h4>
                                            <ul class="list-inline bnr_list_w3 mt-sm-5 mt-3">
                                                <li class="list-inline-item">
                                                    <a class="btn  text-white text-uppercase" href="<?php echo base_url('contact') ?>">contact us</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- //banner slide -->
                        </div>
                        <!-- //carousel item -->
                        <!-- carousel item -->
                        <div class="carousel-item">
                            <!-- banner slide -->
                            <div class="agile_banner bg4 text-center">
                                <div class="layer">
                                    <div class="container">
                                        <div class="banner_text_wthree">
                                            <h4 class="my-3">SURROUNDED BY MOUNTAIN PEAKS</h4>
                                            <ul class="list-inline bnr_list_w3 mt-sm-5 mt-3">
                                                <li class="list-inline-item">
                                                    <a class="btn  text-white text-uppercase" href="<?php echo base_url('contact') ?>">contact us</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- //banner slide -->
                        </div>
                        <!-- //carousel item -->
                    </div>
                </div>
            </section>
        <?php
        } else if ($this->uri->segment(1) == "about" || $this->uri->segment(1) == "gallery" || $this->uri->segment(1) == "contact") {
        ?>
            <section class="inner-banner-agile">
            </section>
        <?php
        }
        ?>
        <!-- //banner slider -->
    </div>
    <!-- banner -->
    <!-- banner bottom -->
    <?php echo $contents; ?>


    <?php $this->load->view('scc/_partials_web/footer.php'); ?>
    <?php $this->load->view('scc/_partials_web/js.php'); ?>


</body>

</html>