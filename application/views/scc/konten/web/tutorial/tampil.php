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
                          <a class="blog_item_date">
                            <h3><?php echo date('d', strtotime($data->tanggal_upload)); ?></h3>
                            <p class="text-white"><?php echo date('M', strtotime($data->tanggal_upload)); ?></p>
                            <p class="text-white"><?php echo date('Y', strtotime($data->tanggal_upload)); ?></p>
                          </a>
                        </div>
                        
                        <div class="blog_details">
                            <a class="d-inline-block" href="single-blog.html">
                                <h2><?php echo $data->judul ?></h2>
                            </a>
                            <p>
                                <?php echo $data->deskripsi ?>
                            </p>
                            <button type="button" class="button button-hero video-btn" data-toggle="modal" data-src="<?php echo $data->embed ?>" data-target="#myModal">
                                Play Tutorial
                            </button>
                        </div>
                      </article>
                      
                      <?php 
                      }
                      ?>
                      
                  </div>
              </div>
              <div class="col-lg-4">
                  <div class="blog_right_sidebar">
                      <aside class="single_sidebar_widget search_widget">
                          <form action="">
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
                      <!-- <aside class="single_sidebar_widget popular_post_widget">
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
                      </aside> -->
                  </div>
              </div>
          </div>
      </div>
  </section>
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        <div class="modal-body">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>        
            <!-- 16:9 aspect ratio -->
            <div class="embed-responsive embed-responsive-16by9">
            <iframe class="embed-responsive-item video_embed" src=""  allowscriptaccess="always" allow="autoplay" allowfullscreen></iframe>
            </div>
        </div>
        </div>
    </div>
</div> 
  
<script src="<?php echo base_url(); ?>_assets/safario/vendors/jquery/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function() {

// Gets the video src from the data-src on each button

var $videoSrc;  
$('.video-btn').click(function() {
    $videoSrc = $(this).data( "src" );
});


  
  
// when the modal is opened autoplay it  
$('#myModal').on('shown.bs.modal', function (e) {
    
// set the video src to autoplay and not to show related video. Youtube related video is like a box of chocolates... you never know what you're gonna get
$(".video_embed").attr('src',$videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0" ); 
})
  


// stop playing the youtube video when I close the modal
$('#myModal').on('hide.bs.modal', function (e) {
    // a poor man's stop video
    $(".video_embed").attr('src',$videoSrc); 
}) 
    
    


  
  
// document ready  
});
</script>