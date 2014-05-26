
#1) Atualização do gerenciador de pacotes
exec { "apt-update":
  command => "apt-get -y update",
  path    => "/usr/bin:/usr/sbin:/bin:/usr/local/bin",
}
Exec["apt-update"] -> Package <| |>


#2) Instalação do servidor de banco de dados PostgreSQL
class { 'postgresql::server':
  ip_mask_deny_postgres_user => '0.0.0.0/32',
  ip_mask_allow_all_users    => '0.0.0.0/0',
  listen_addresses           => '*',
#  ipv4acls                   => ['hostssl all johndoe 192.168.0.0/24 cert'],
  manage_firewall            => true,
  postgres_password          => 'postgres',
}

#3) Construção do banco de dados da aplicação
postgresql::server::db { 'planocerto':
  user     => 'planocerto',
  password => postgresql_password('planocerto', 'planocerto'),
}
