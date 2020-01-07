<div class="container-fluid">
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h5 class="m-0 font-weight-bold text-primary">Data Anggota Internal</h5>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					<thead>
						<tr>
							<th width="5%" class="text-center">No</th>
							<th width="20%">Nama</th>
							<th width="15%">No. HP</th>
							<th width="15%">ID Line</th>
							<th width="15%">Username</th>
							<th width="10%">Jabatan</th>
							<th width="5%">Managerial</th>
							<th width="5%">Status</th>
							<th width="10%" class="text-center">Aksi</th>
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
							<td><?= $data->username ?></td>
							<td><?= $data->hak_akses ?></td>
							<td><?= $data->jabatan_managerial ?></td>
							<td><?= $data->status_sj ?></td>
							<td class="text-center">
								<a href="<?= base_url('admin/jadwal_prove/jadwal/'.$data->id_internal) ?>"
									class="btn btn-sm btn-info">Jadwal</a>
							</td>
						</tr>
						<?php endforeach; ?>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
