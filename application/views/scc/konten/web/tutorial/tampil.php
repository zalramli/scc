<section class="hero-banner magic-ball">
    <div class="container">

    <div class="row align-items-center text-center text-md-left">
        <div class="col-md-6 col-lg-5 mb-5 mb-md-0">
        <h1>Tutorial</h1>
        <p>
        Berisi tentang video tutorial materi materi tentang komputasi statistika.
        </p>  
        </div>
        <div class="col-md-6 col-lg-7 col-xl-6 offset-xl-1">
        <img class="img-fluid" src="<?php echo base_url();?>_assets/safario/img/home/hero-img-tutorial.png" alt="">
        </div>
    </div>
    </div>
</section>

<section class="blog_area section-margin-large">
      <div class="container">
          <div class="row">
              <div class="col-lg-8 mb-5 mb-lg-0">
                  <div class="blog_left_sidebar">
                      <?php 
                      foreach($record as $data)
                      {
                      ?>
                      <article class="blog_item">
                        <div class="blog_item_img">
                          <img class="card-img rounded-0" src="<?php echo base_url() ?>_assets/image_web/<?php echo $data->foto ?>" alt="">
                          <a href="#" class="blog_item_date">
                            <h3>15</h3>
                            <p>Jan</p>
                          </a>
                        </div>
                        
                        <div class="blog_details">
                            <a class="d-inline-block" href="single-blog.html">
                                <h2><?php echo $data->judul ?></h2>
                            </a>
                            <p>
                                <?php echo $data->deskripsi ?>
                            </p>
                            <ul class="blog-info-link">
                              <li><a href="#"><i class="far fa-user"></i> Travel, Lifestyle</a></li>
                              <li><a href="#"><i class="far fa-comments"></i> 03 Comments</a></li>
                            </ul>
                        </div>
                      </article>
                      <?php 
                      }
                      ?>
                      


                      <nav class="blog-pagination justify-content-center d-flex">
                          <ul class="pagination">
                              <li class="page-item">
                                  <a href="#" class="page-link" aria-label="Previous">
                                      <span aria-hidden="true">
                                          <span class="lnr lnr-chevron-left"></span>
                                      </span>
                                  </a>
                              </li>
                              <li class="page-item">
                                  <a href="#" class="page-link">1</a>
                              </li>
                              <li class="page-item active">
                                  <a href="#" class="page-link">2</a>
                              </li>
                              <li class="page-item">
                                  <a href="#" class="page-link" aria-label="Next">
                                      <span aria-hidden="true">
                                          <span class="lnr lnr-chevron-right"></span>
                                      </span>
                                  </a>
                              </li>
                          </ul>
                      </nav>
                  </div>
              </div>
              <div class="col-lg-4">
                  <div class="blog_right_sidebar">
                      <aside class="single_sidebar_widget search_widget">
                          <form action="#">
                            <div class="form-group">
                              <div class="input-group mb-3">
                                <input type="text" class="form-control" placeholder="Search Keyword">
                                <div class="input-group-append">
                                  <button class="btn" type="button"><i class="ti-search"></i></button>
                                </div>
                              </div>
                            </div>
                            <button class="button rounded-0 primary-bg text-white w-100" type="submit">Search</button>
                          </form>
                      </aside>

                      <!-- <aside class="single_sidebar_widget post_category_widget">
                        <h4 class="widget_title">Category</h4>
                        <ul class="list cat-list">
                            <li>
                                <a href="#" class="d-flex">
                                    <p>Resaurant food</p>
                                    <p>(37)</p>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="d-flex">
                                    <p>Travel news</p>
                                    <p>(10)</p>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="d-flex">
                                    <p>Modern technology</p>
                                    <p>(03)</p>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="d-flex">
                                    <p>Product</p>
                                    <p>(11)</p>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="d-flex">
                                    <p>Inspiration</p>
                                    <p>21</p>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="d-flex">
                                    <p>Health Care (21)</p>
                                    <p>09</p>
                                </a>
                            </li>
                        </ul>
                      </aside> -->

                      <aside class="single_sidebar_widget popular_post_widget">
                          <h3 class="widget_title">Recent Post</h3>
                          <div class="media post_item">
                              <img src="<?php echo base_url() ?>_assets/safario/img/blog/popular-post/post1.jpg" alt="post">
                              <div class="media-body">
                                  <a href="single-blog.html">
                                      <h3>From life was you fish...</h3>
                                  </a>
                                  <p>January 12, 2019</p>
                              </div>
                          </div>
                          <div class="media post_item">
                              <img src="<?php echo base_url() ?>_assets/safario/img/blog/popular-post/post2.jpg" alt="post">                              
                              <div class="media-body">
                                  <a href="single-blog.html">
                                      <h3>The Amazing Hubble</h3>
                                  </a>
                                  <p>02 Hours ago</p>
                              </div>
                          </div>
                          <div class="media post_item">
                              <img src="<?php echo base_url() ?>_assets/safario/img/blog/popular-post/post3.jpg" alt="post">                              
                              <div class="media-body">
                                  <a href="single-blog.html">
                                      <h3>Astronomy Or Astrology</h3>
                                  </a>
                                  <p>03 Hours ago</p>
                              </div>
                          </div>
                          <div class="media post_item">
                              <img src="<?php echo base_url() ?>_assets/safario/img/blog/popular-post/post4.jpg" alt="post">
                              <div class="media-body">
                                  <a href="single-blog.html">
                                      <h3>Asteroids telescope</h3>
                                  </a>
                                  <p>01 Hours ago</p>
                              </div>
                          </div>
                      </aside>
                  </div>
              </div>
          </div>
      </div>
  </section>