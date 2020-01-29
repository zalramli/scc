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
			<h5 class="m-0 font-weight-bold text-primary">Data Anggota Internal</h5>
		</div>
		<div class="card-body">
			<button type="button" class="btn btn-sm btn-primary mb-3" data-toggle="modal" data-target=".bd-example-modal-lg">Tambah</button>

			<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Tambah Anggota Internal</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<?php echo form_open('admin/internal/store'); ?>
						<div class="modal-body">
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputEmail2">Nama</label>
									<input type="text" name="nama" class="form-control form-control-sm karakter" id="inputEmail2" placeholder="Masukan nama anggota" required>
								</div>
								<div class="form-group col-md-6">
									<label for="inputEmail3">Status</label>
									<select name="status_sj" class="form-control form-control-sm" id="inputEmail5" required>
										<option value="Senior">Senior</option>
										<option value="Junior">Junior</option>
									</select>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputEmail2">Jabatan</label>
									<select name="hak_akses" class="form-control form-control-sm" id="inputEmail5" required>
										<option value="Kadiv">Kadiv</option>
										<option value="Sekdiv">Sekdiv</option>
										<option value="Bendahara">Bendahara</option>
										<option value="Staff Ahli">Staff Ahli</option>
										<option value="Manager">Manager</option>
										<option value="Staff">Staff</option>
									</select>
								</div>
								<div class="form-group col-md-6">
									<label for="inputEmail3">Managerial</label>
									<select name="jabatan_managerial" class="form-control form-control-sm" id="inputEmail5" required>
										<option value="TD">TD</option>
										<option value="HRD">HRD</option>
										<option value="PR">PR</option>
										<option value="Kosong">Kosong</option>
									</select>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputEmail2">No Hp</label>
									<input type="text" name="no_hp" class="form-control form-control-sm no_hp" id="inputEmail2" placeholder="Contoh : 0822-xxxx-xxx" required>
								</div>
								<div class="form-group col-md-6">
									<label for="inputEmail2">ID Line</label>
									<input type="text" name="akun_line" class="form-control form-control-sm" id="inputEmail2" placeholder="Masukan ID line" required>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputEmail2">Username</label>
									<input type="text" name="username" class="form-control form-control-sm" id="inputEmail2" placeholder="Masukan username" required>
								</div>
								<div class="form-group col-md-6">
									<label for="inputEmail2">Password</label>
									<input type="text" name="password" class="form-control form-control-sm" id="inputEmail2" placeholder="Masukan password" required>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">Simpan</button>
							<button type="button" class="btn btn-link" data-dismiss="modal">Kembali</button>
						</div>
						<?php echo form_close(); ?>
					</div>
				</div>
			</div>
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th width="5%" class="text-center">No</th>
							<th width="20%">Nama</th>
							<th width="10%">No. HP</th>
							<th width="15%">ID Line</th>
							<th width="15%">Username</th>
							<th width="10%">Jabatan</th>
							<th width="5%">Managerial</th>
							<th width="5%">Status</th>
							<th width="15%" class="text-center">Aksi</th>
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
								<td><?= $data->no_hp ?></td>
								<td><?= $data->akun_line ?></td>
								<td><?= $data->username ?></td>
								<td><?= $data->hak_akses ?></td>
								<td><?= $data->jabatan_managerial ?></td>
								<td><?= $data->status_sj ?></td>
								<td class="text-center">
									<a style="cursor:pointer" class="btn btn-sm btn-warning text-white mb-1" data-toggle="modal" data-target="#modal-edit<?= $data->id_internal ?>"><i class="fas fa-edit"></i></a>
									<a href="<?= base_url('admin/internal/delete/' . $data->id_internal) ?>" class="btn btn-sm btn-danger tombol-hapus"><i class="fas fa-trash"></i></a>
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
	<div id="modal-edit<?= $data->id_internal; ?>" class="modal fade">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Edit Data peserta</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<?php echo form_open('admin/internal/update'); ?>
				<div class="modal-body">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail2">Nama</label>
							<input type="hidden" name="id_internal" value="<?= $data->id_internal ?>">
							<input type="text" name="nama" class="form-control form-control-sm karakter" id="inputEmail2" value="<?= $data->nama ?>" placeholder="Masukan nama anggota" required>
						</div>
						<div class="form-group col-md-6">
							<label for="inputEmail3">Status</label>
							<select name="status_sj" class="form-control form-control-sm" id="inputEmail5" required>
								<option value="Senior" <?php if ($data->status_sj == 'Senior') echo 'selected'; ?>>Senior
								</option>
								<option value="Junior" <?php if ($data->status_sj == 'Junior') echo 'selected'; ?>>Junior
								</option>
							</select>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail2">Jabatan</label>
							<select name="hak_akses" class="form-control form-control-sm" id="inputEmail5" required>
								<option value="Kadiv" <?php if ($data->hak_akses == 'Kadiv') echo 'selected'; ?>>Kadiv
								</option>
								<option value="Sekdiv" <?php if ($data->hak_akses == 'Sekdiv') echo 'selected'; ?>>Sekdiv
								</option>
								<option value="Bendahara" <?php if ($data->hak_akses == 'Bendahara') echo 'selected'; ?>>
									Bendahara</option>
								<option value="Staff Ahli" <?php if ($data->hak_akses == 'Staff Ahli') echo 'selected'; ?>>
									Staff
									Ahli</option>
								<option value="Manager" <?php if ($data->hak_akses == 'Manager') echo 'selected'; ?>>Manager
								</option>
								<option value="Staff" <?php if ($data->hak_akses == 'Staff') echo 'selected'; ?>>Staff
								</option>
							</select>
						</div>
						<div class="form-group col-md-6">
							<label for="inputEmail3">Managerial</label>
							<select name="jabatan_managerial" class="form-control form-control-sm" id="inputEmail5" required>
								<option value="TD" <?php if ($data->jabatan_managerial == 'TD') echo 'selected'; ?>>TD
								</option>
								<option value="HRD" <?php if ($data->jabatan_managerial == 'HRD') echo 'selected'; ?>>HRD
								</option>
								<option value="PR" <?php if ($data->jabatan_managerial == 'PR') echo 'selected'; ?>>PR
								</option>
								<option value="Kosong" <?php if ($data->jabatan_managerial == 'Kosong') echo 'selected'; ?>>
									Kosong</option>
							</select>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail2">No Hp</label>
							<input type="text" name="no_hp" class="form-control form-control-sm no_hp" id="inputEmail2" value="<?= $data->no_hp ?>" placeholder="Contoh : 0822-xxxx-xxx" required>
						</div>
						<div class="form-group col-md-6">
							<label for="inputEmail2">ID Line</label>
							<input type="text" name="akun_line" class="form-control form-control-sm" id="inputEmail2" value="<?= $data->akun_line ?>" placeholder="Masukan ID line" required>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail2">Username</label>
							<input type="text" name="username" class="form-control form-control-sm" id="inputEmail2" value="<?= $data->username ?>" placeholder="Masukan username" required>
						</div>
						<div class="form-group col-md-6">
							<label for="inputEmail2">Password</label>
							<input type="text" name="password" class="form-control form-control-sm" id="inputEmail2" value="<?= $data->password ?>" placeholder="Masukan password" required>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">Simpan</button>
					<button type="button" class="btn btn-link" data-dismiss="modal">Kembali</button>
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