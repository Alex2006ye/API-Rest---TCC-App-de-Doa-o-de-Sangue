-- 1. Campanha: exclusão em cascata ao excluir o hemocentro
ALTER TABLE campanha
DROP FOREIGN KEY FKf9q325k39uls58y7ui8m0q4c0;

ALTER TABLE campanha
ADD CONSTRAINT FKf9q325k39uls58y7ui8m0q4c0
FOREIGN KEY (id_usuario_hemocentro)
REFERENCES usuario(id)
ON DELETE CASCADE;


-- 2. Agendamento: exclusão em cascata ao excluir o hemocentro
ALTER TABLE agendamento
DROP FOREIGN KEY FK5xdw3trbj4gmfg78vqid50cgv;

ALTER TABLE agendamento
ADD CONSTRAINT FK5xdw3trbj4gmfg78vqid50cgv
FOREIGN KEY (id_usuario_hemocentro)
REFERENCES usuario(id)
ON DELETE CASCADE;


-- 3. Agendamento: exclusão em cascata ao excluir o doador
ALTER TABLE agendamento
DROP FOREIGN KEY FKoj6tdj4sy04lko06vg4sshx3m;

ALTER TABLE agendamento
ADD CONSTRAINT FKoj6tdj4sy04lko06vg4sshx3m
FOREIGN KEY (id_usuario_doador)
REFERENCES usuario(id)
ON DELETE CASCADE;


-- 4. Agendamento: exclusão em cascata ao excluir a campanha
ALTER TABLE agendamento
DROP FOREIGN KEY FKiy2aiqbd6q786od1wxbj6ofok;

ALTER TABLE agendamento
ADD CONSTRAINT FKiy2aiqbd6q786od1wxbj6ofok
FOREIGN KEY (id_campanha)
REFERENCES campanha(id_campanha)
ON DELETE CASCADE;