<?php if ($this->session->flashdata('success')) : ?>
	<div class="pesan-sukses" data-flashdata="<?= $this->session->flashdata('success'); ?>"></div>
<?php endif; ?>
<?php if ($this->session->flashdata('update')) : ?>
	<div class="pesan-update" data-flashdata="<?= $this->session->flashdata('update'); ?>"></div>
<?php endif; ?>
<?php if ($this->session->flashdata('hapus')) : ?>
	<div class="pesan-hapus" data-flashdata="<?= $this->session->flashdata('hapus'); ?>"></div>
<?php endif; ?>
<?php if ($this->session->flashdata('upload')) : ?>
	<div class="pesan-upload" data-flashdata="<?= $this->session->flashdata('upload'); ?>"></div>
<?php endif; ?>
<div class="container-fluid">
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Data Tutorial</h6>
		</div>
		<div class="card-body">
			<button type="button" class="btn btn-sm btn-primary mb-3" data-toggle="modal" data-target=".bd-example-modal-lg">Tambah</button>

			<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Tambah Data Tutorial</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form action="<?= base_url('admin/tutorial/store') ?>" method="post" enctype="multipart/form-data">
						<div class="modal-body">
							<div class="form-row">
								<div class="form-group col-sm-6">
									<label for="inputEmail2">Judul</label>
									<input type="text" name="judul" class="form-control form-control-sm" id="inputEmail2" placeholder="Masukan judul tutorial" required>
								</div>
                                <div class="form-group col-sm-6">
									<label for="inputEmail2">Foto</label>
									<input type="file" name="foto" class="form-control form-control-sm" id="inputEmail2" placeholder="Masukan judul tutorial" required>
								</div>
							</div>
                            <div class="form-row">
								<div class="form-group col-sm-6">
									<label for="inputEmail2">Link Youtube</label>
									<input type="text" name="embed" class="form-control form-control-sm" id="inputEmail2" placeholder="Masukan link tutorial" required>
								</div>
                                <div class="form-group col-sm-6">
									<label for="inputEmail2">Deskripsi</label>
                                    <textarea name="deskripsi" class="form-control form-control-sm" rows="12" id="inputEmail2"></textarea>
                                </div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-sm btn-success">Simpan</button>
							<button type="button" class="btn btn-sm btn-link" data-dismiss="modal">Kembali</button>
						</div>
						</form>
					</div>
				</div>
			</div>
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th class="text-center">No</th>
							<th class="text-center">Judul</th>
							<th class="text-center">Foto</th>
							<th class="text-center">Link Youtube</th>
							<th class="text-center">Tanggal Upload</th>
							<th class="text-center">Aksi</th>
						</tr>
					</thead>
					<tbody>
						<?php
						$no = 1;
						foreach ($record as $data) :
						?>
							<tr>
								<td class="text-center"><?= $no++ ?></td>
								<td><?= $data->judul ?></td>
								<td class="text-center"><img width="100px" height="100px" src="<?php echo base_url(); ?>_assets/image_web/<?= $data->foto ?>" alt=""></td>
								<td><?= $data->embed ?></td>
								<td><?= date('d-m-Y',strtotime($data->tanggal_upload)) ?></td>
								<td class="text-center">
									<a style="cursor:pointer" class="btn btn-sm btn-warning text-white" data-toggle="modal" data-target="#modal-edit<?= $data->id_tutorial ?>">Edit</a>
									<a href="<?= base_url('admin/tutorial/delete/' . $data->id_tutorial) ?>" class="btn btn-sm btn-danger tombol-hapus">Hapus</a>
								</td>
							</tr>
						<?php endforeach; ?>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- Modal Edit -->
<?php foreach ($record as $data) :  ?>
	<div id="modal-edit<?= $data->id_tutorial; ?>" class="modal fade">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Edit Data Tutorial</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="<?= base_url('admin/tutorial/update') ?>" method="post" enctype="multipart/form-data">
						<div class="modal-body">
							<div class="form-row">
								<div class="form-group col-sm-6">
									<label for="inputEmail2">Judul</label>
                                    <input type="hidden" name="id_tutorial" value="<?= $data->id_tutorial ?>">
									<input type="text" name="judul" class="form-control form-control-sm" id="inputEmail2" placeholder="Masukan judul tutorial" value="<?= $data->judul ?>" required>
								</div>
                                <div class="form-group col-sm-6">
									<label for="inputEmail2">Foto</label>
									<input type="file" name="foto" class="form-control form-control-sm" id="inputEmail2" placeholder="Masukan judul tutorial" >
								</div>
							</div>
                            <div class="form-row">
								<div class="form-group col-sm-6">
									<label for="inputEmail2">Link Youtube</label>
									<input type="text" name="embed" class="form-control form-control-sm" id="inputEmail2" placeholder="Masukan link tutorial" value="<?= $data->embed ?>" required>
								</div>
                                <div class="form-group col-sm-6">
									<label for="inputEmail2">Deskripsi</label>
                                    <textarea name="deskripsi" class="form-control form-control-sm" rows="12" id="inputEmail2"><?= $data->deskripsi ?></textarea>
                                </div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-sm btn-success">Simpan</button>
							<button type="button" class="btn btn-sm btn-link" data-dismiss="modal">Kembali</button>
						</div>
						</form>
			</div>
		</div>
	</div>
<?php endforeach; ?>
<script src="<?= base_url(); ?>_assets/sb_admin_2/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript">
	$('.tombol-hapus').on('click', function(e) {
		e.preventDefault();
		var href = $(this).attr('href');
		Swal.fire({
			title: 'Apakah anda yakin?',
			text: "Data user akan dihapus",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Hapus'
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