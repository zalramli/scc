<?php if($this->session->flashdata('success')) : ?>
<div class="pesan-sukses" data-flashdata="<?= $this->session->flashdata('success'); ?>"></div>
<?php endif; ?>
<?php if($this->session->flashdata('update')) : ?>
<div class="pesan-update" data-flashdata="<?= $this->session->flashdata('update'); ?>"></div>
<?php endif; ?>
<?php if($this->session->flashdata('hapus')) : ?>
<div class="pesan-hapus" data-flashdata="<?= $this->session->flashdata('hapus'); ?>"></div>
<?php endif; ?>
<div class="container-fluid">
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h5 class="m-0 font-weight-bold text-primary">Jadwal Prove <?= $nama ?></h5>
		</div>
		<div class="card-body">
			<div class="col-sm-12">
				<a href="<?= base_url('admin/jadwal_prove') ?>" class="btn btn-sm btn-dark mb-3">Kembali</a>
				<button type="button" class="btn btn-sm btn-primary mb-3" data-toggle="modal"
					data-target=".bd-example-modal-lg">Tambah Jadwal</button>

				<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog"
					aria-labelledby="myLargeModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Tambah Jadwal Prove</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<?php echo form_open('admin/jadwal_prove/store_jadwal'); ?>
							<div class="modal-body">
								<div class="form-row">
									<div class="form-group col-sm-4">
										<label for="inputEmail3">Hari</label>
										<input type="hidden" name="id_internal" value=<?= $id_internal ?>>
										<select name="hari" class="form-control form-control-sm" id="inputEmail5"
											required>
											<option value="Senin">Senin</option>
											<option value="Selasa">Selasa</option>
											<option value="Rabu">Rabu</option>
											<option value="Kamis">Kamis</option>
											<option value="Jumat">Jumat</option>
										</select>
									</div>
									<div class="form-group col-sm-4">
										<div class="row">
											<div class="col-sm-6">
												<label for="inputEmail3">Jam Mulai</label>
												<select name="jam_mulai" class="form-control form-control-sm"
													id="inputEmail5" required>
													<option value="07">07</option>
													<option value="08">08</option>
													<option value="09">09</option>
													<option value="10">11</option>
													<option value="12">12</option>
													<option value="13">13</option>
													<option value="14">14</option>
													<option value="15">15</option>
													<option value="16">16</option>
												</select>
											</div>
											<div class="col-sm-6">
												<label for="inputEmail3">Menit Mulai</label>

												<select name="menit_mulai" class="form-control form-control-sm"
													id="inputEmail5" required>
													<option value="00">00</option>
													<option value="15">15</option>
													<option value="30">30</option>
													<option value="45">45</option>
												</select>
											</div>
										</div>

									</div>
									<div class="form-group col-sm-4">
										<div class="row">
											<div class="col-sm-6">
												<label for="inputEmail3">Jam Selesai</label>
												<select name="jam_selesai" class="form-control form-control-sm"
													id="inputEmail5" required>
													<option value="07">07</option>
													<option value="08">08</option>
													<option value="09">09</option>
													<option value="10">11</option>
													<option value="12">12</option>
													<option value="13">13</option>
													<option value="14">14</option>
													<option value="15">15</option>
													<option value="16">16</option>
												</select>
											</div>
											<div class="col-sm-6">
												<label for="inputEmail3">Menit Selesai</label>

												<select name="menit_selesai" class="form-control form-control-sm"
													id="inputEmail5" required>
													<option value="00">00</option>
													<option value="15">15</option>
													<option value="30">30</option>
													<option value="45">45</option>
												</select>
											</div>
										</div>
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
				<div class="row">
					<div class="col-sm-4 mb-4">
						<div class="card" style="width: 18rem;">
							<div class="card-header text-center">
								Senin
							</div>
							<ul class="list-group list-group-flush">
								<?php
                                    $no=1;
                                    foreach($record_senin as $data_senin):
                                ?>
								<li class="list-group-item" id="table1">
									<?= $data_senin->jam_mulai.' - '.$data_senin->jam_selesai ?>
									<select class="float-right status" id="ubah_status">
										<option value="<?= $data_senin->id_jadwal_prove ?>-Aktif"
											<?php if($data_senin->status_aktif== 'Aktif') echo 'selected'; ?>>Aktif
										</option>
										<option value="<?= $data_senin->id_jadwal_prove ?>-Tidak Aktif"
											<?php if($data_senin->status_aktif== 'Tidak Aktif') echo 'selected'; ?>>
											Tidak Aktif
										</option>
									</select>
								</li>
								<?php endforeach; ?>

							</ul>
						</div>
					</div>
					<div class="col-sm-4 mb-4">
						<div class="card" style="width: 18rem;">
							<div class="card-header text-center">
								Selasa
							</div>
							<ul class="list-group list-group-flush">
								<?php
                                    $no=1;
                                    foreach($record_selasa as $data_selasa):
                                ?>
								<li class="list-group-item">
									<?= $data_selasa->jam_mulai.' - '.$data_selasa->jam_selesai ?></li>
								<?php endforeach; ?>

							</ul>
						</div>
					</div>
					<div class="col-sm-4 mb-4">
						<div class="card" style="width: 18rem;">
							<div class="card-header text-center">
								Rabu
							</div>
							<ul class="list-group list-group-flush">
								<?php
                                    $no=1;
                                    foreach($record_rabu as $data_rabu):
                                ?>
								<li class="list-group-item">
									<?= $data_rabu->jam_mulai.' - '.$data_rabu->jam_selesai ?></li>
								<?php endforeach; ?>

							</ul>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="card" style="width: 18rem;">
							<div class="card-header text-center">
								Kamis
							</div>
							<ul class="list-group list-group-flush">
								<?php
                                    $no=1;
                                    foreach($record_kamis as $data_kamis):
                                ?>
								<li class="list-group-item">
									<?= $data_kamis->jam_mulai.' - '.$data_kamis->jam_selesai ?></li>
								<?php endforeach; ?>

							</ul>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="card" style="width: 18rem;">
							<div class="card-header text-center">
								Jum'at
							</div>
							<ul class="list-group list-group-flush">
								<?php
                                    $no=1;
                                    foreach($record_jumat as $data_jumat):
                                ?>
								<li class="list-group-item">
									<?= $data_jumat->jam_mulai.' - '.$data_jumat->jam_selesai ?></li>
								<?php endforeach; ?>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="<?= base_url(); ?>assets/sb_admin_2/vendor/jquery/jquery.min.js"></script>
<script>
	$('.status').change(function () {
		var id = this.value;
		$.ajax({
			url: "<?php echo base_url() . 'admin/jadwal_prove/update_jadwal_senin'; ?>",
			method: "POST",
			data: {
				id: id
			},
			success: function (data) {
				location.reload();
			}
		});
	});

</script>
