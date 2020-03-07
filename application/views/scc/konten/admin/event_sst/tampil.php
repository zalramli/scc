<?php if ($this->session->flashdata('success')) : ?>
	<div class="pesan-sukses" data-flashdata="<?= $this->session->flashdata('success'); ?>"></div>
<?php endif; ?>
<?php if ($this->session->flashdata('update')) : ?>
	<div class="pesan-update" data-flashdata="<?= $this->session->flashdata('update'); ?>"></div>
<?php endif; ?>
<?php if ($this->session->flashdata('hapus')) : ?>
	<div class="pesan-hapus" data-flashdata="<?= $this->session->flashdata('hapus'); ?>"></div>
<?php endif; ?>
<div class="container-fluid">
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Pendaftaran SST</h6>
		</div>
		<div class="card-body">
		    <h4>JUMLAH PENDAFTAR : <?php echo $jumlah_pendaftar; ?></h4>
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th class="text-center">No</th>
							<th class="text-center">Nama</th>
							<th class="text-center">Email</th>
							<th class="text-center">Institusi</th>
							<th class="text-center">Jurusan</th>
							<th class="text-center">No Hp</th>
							<th class="text-center">Line</th>
							<th class="text-center">Alasan</th>
							<th class="text-center">Status</th>
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
								<td><?= $data->nama ?></td>
								<td><?= $data->email ?></td>
								<td><?= $data->institusi ?></td>
								<td><?= $data->jurusan ?></td>
								<td><?= $data->no_hp ?></td>
								<td><?= $data->akun_line ?></td>
								<td><?= $data->alasan ?></td>
								<td><?= $data->status ?></td>
								<td class="text-center">
									<a style="cursor:pointer" class="btn btn-sm btn-warning text-white" data-toggle="modal" data-target="#modal-edit<?= $data->id_event_sst ?>">Edit</a>
									<a href="<?= base_url('admin/sst/delete/' . $data->id_event_sst) ?>" class="btn btn-sm btn-danger tombol-hapus">Hapus</a>
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
	<div id="modal-edit<?= $data->id_event_sst; ?>" class="modal fade">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Edit Status Pembayaran</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<?php echo form_open('admin/sst/update'); ?>
				<div class="modal-body">
					<div class="form-row">
						<div class="form-group col-sm-6">
							<input type="hidden" name="id_event_sst" value="<?= $data->id_event_sst ?>">
							<label for="inputEmail2">Nama</label>
							<input type="text" name="nama" value="<?= $data->nama ?>" class="form-control form-control-sm karakter" id="inputEmail2" placeholder="Masukan nama materi" required>

						</div>
						<div class="form-group col-sm-6">
							<label for="inputEmail2">Status Pembayaran</label>
							<select name="status" class="form-control form-control-sm">
								<option value="Belum" <?php if($data->status=="Belum"){echo"selected";} ?>>Belum</option>
								<option value="Lunas" <?php if($data->status=="Lunas"){echo"selected";} ?>>Lunas</option>
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-sm btn-success">Update</button>
					<button type="button" class="btn btn-sm btn-link" data-dismiss="modal">Kembali</button>
				</div>
				<?php echo form_close(); ?>
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