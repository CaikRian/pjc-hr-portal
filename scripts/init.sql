-- Seed opcional (roda na primeira inicialização do container)
INSERT INTO candidate (name, email, role, seniority, created_at)
VALUES
  ('João Pereira', 'joao@exemplo.com', 'Front-end Jr', 'Junior', NOW()),
  ('Maria Souza', 'maria@exemplo.com', 'Dev Back-end Jr', 'Junior', NOW());
