<?php if ($this->session->flashdata('update')) : ?>
	<div class="pesan-update" data-flashdata="<?= $this->session->flashdata('update'); ?>"></div>
<?php endif; ?>
<div class="container-fluid">
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h5 class="m-0 font-weight-bold text-primary">Data Jadwal Pengajar Prove</h5>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th class="text-center">No</th>
							<th class="text-center">Nama</th>
							<th class="text-center">No. HP</th>
							<th class="text-center">ID Line</th>
							<th class="text-center">Jadwal</th>
							<th class="text-center">Aksi</th>
						</tr>
					</thead>
					<tbody>
						<?php
						$no=1;
						foreach($record as $data):
                        ?>
						<tr>
							<td class="text-center"><?= $no++ ?></td>
							<td><?= $data->nama ?></td>
							<td><?= $data->no_hp ?></td>
							<td><?= $data->akun_line ?></td>
							<td class="text-center">
								<a href="<?= base_url('admin/jadwal_prove/jadwal/'.$data->id_internal) ?>"
									class="btn btn-sm btn-info">Lihat</a>
							</td>
							<td class="text-center">
								<a href="<?= base_url('admin/jadwal_prove/ubah_aktif/'.$data->id_internal) ?>"
									class="btn btn-sm btn-success tombol-ubah-satu">Aktif</a>
									<a href="<?= base_url('admin/jadwal_prove/ubah_tidak_aktif/'.$data->id_internal) ?>"
									class="btn btn-sm btn-danger tombol-ubah-dua">Tidak Aktif</a>
							</td>
						</tr>
						<?php endforeach; ?>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script src="<?= base_url(); ?>_assets/sb_admin_2/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript">
	$('.tombol-ubah-satu').on('click', function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		Swal.fire({
			title: 'Apakah anda yakin?',
			text: "Jadwal pengajar akan di setting menjadi aktif",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Ubah'
		}).then((result) => {
			if (result.value) {
				document.location.href = href;
				// Swal.fire(
				// 	'Deleted!',
				// 	'Your file has been deleted.',
				// 	'success'
				// )
			}
		})
	});
</script>
<script type="text/javascript">
	$('.tombol-ubah-dua').on('click', function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		Swal.fire({
			title: 'Apakah anda yakin?',
			text: "Jadwal pengajar akan di setting menjadi tidak aktif",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Ubah'
		}).then((result) => {
			if (result.value) {
				document.location.href = href;
				// Swal.fire(
				// 	'Deleted!',
				// 	'Your file has been deleted.',
				// 	'success'
				// )
			}
		})
	});
</script>