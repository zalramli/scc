<!-- nav -->
<a href="#menu" class="menu-link">
    <span>toggle menu</span>
</a>
<nav id="menu" class="panel">
    <ul>
        <li>
            <a href="https://scc-himastaits.com" <?php if ($this->uri->segment(1) == "" || $this->uri->segment(1) == "home") {
                                                        echo 'class="active"';
                                                    } ?>>Home</a>
        </li>
        <li>
            <a href="<?php echo base_url('about') ?>" <?php if ($this->uri->segment(1) == "about") {
                                                            echo 'class="active"';
                                                        } ?>>About</a>
        </li>
        <li>
            <a href="<?php echo base_url('gallery') ?>" <?php if ($this->uri->segment(1) == "gallery") {
                                                            echo 'class="active"';
                                                        } ?>>Gallery</a>
        </li>
        <li>
            <a href="<?php echo base_url('contact') ?>" <?php if ($this->uri->segment(1) == "contact") {
                                                            echo 'class="active"';
                                                        } ?>>Contact</a>
        </li>

    </ul>
</nav>
<!-- /Nav -->