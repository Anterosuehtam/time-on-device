package com.project.time_on_device.service;

import com.project.time_on_device.entity.ActivityLog;
import com.project.time_on_device.entity.User;
import com.project.time_on_device.repository.ActivityLogRepository;
import com.project.time_on_device.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityLogService {

    @Autowired
    private ActivityLogRepository activityLogRepository;
    @Autowired
    private UserRepository userRepository;

    // CREATE (POST)
    public ActivityLog createActivityLog(ActivityLog activityLog) {
        if (activityLog.getStartTime() != null && activityLog.getEndTime() != null) {
            // Calcula a duração de uma NOVA atividade
            long minutes = Duration.between(activityLog.getStartTime(), activityLog.getEndTime()).toMinutes();
            activityLog.setDurationMinutes((int) minutes);

            // Busca o dono da atividade no banco
            User donoDaAtividade = userRepository.findById(activityLog.getUser().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));

            if (donoDaAtividade.getDailyLimitMinutes() != null) {

                // Descobre qual é o "início" e o "fim" do dia da atividade que está sendo salva
                LocalDateTime inicioDoDia = activityLog.getStartTime().toLocalDate().atStartOfDay(); // Ex: 2026-02-27 00:00:00
                LocalDateTime fimDoDia = activityLog.getStartTime().toLocalDate().atTime(23, 59, 59); // Ex: 2026-02-27 23:59:59

                // Busca todas as atividades que este usuário já fez NESTE DIA
                List<ActivityLog> atividadesDeHoje = activityLogRepository
                        .findByUserIdAndStartTimeBetween(donoDaAtividade.getId(), inicioDoDia, fimDoDia);

                // Soma os minutos de todas as atividades de hoje
                int minutosJaUsadosHoje = 0;
                for (ActivityLog atividade : atividadesDeHoje) {
                    if (atividade.getDurationMinutes() != null) {
                        minutosJaUsadosHoje += atividade.getDurationMinutes();
                    }
                }

                // Descobre o saldo restante
                int saldoRestante = donoDaAtividade.getDailyLimitMinutes() - minutosJaUsadosHoje;

                // Verifica se a nova atividade cabe no saldo
                if (minutes > saldoRestante) {
                    throw new IllegalArgumentException(
                            "Bloqueado: Você já usou " + minutosJaUsadosHoje +
                                    " min hoje. Seu saldo restante é de apenas " + saldoRestante +
                                    " min, mas você tentou registrar " + minutes + " min!"
                    );
                }
            }
        }
        return activityLogRepository.save(activityLog);
    }

    // READ (GET)
    public List<ActivityLog> getAllActivities () {
        return activityLogRepository.findAll();
    }

    // UPDATE (PUT)
    public ActivityLog updateActivityLog(Integer id, ActivityLog atividadeAtualizada) {

        ActivityLog atividadeExistente = activityLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atividade não encontrada!"));

        if (atividadeAtualizada.getAppOrSiteName() != null) {
            atividadeExistente.setAppOrSiteName(atividadeAtualizada.getAppOrSiteName());
        }

        if (atividadeAtualizada.getStartTime() != null) {
            atividadeExistente.setStartTime(atividadeAtualizada.getStartTime());
        }

        if (atividadeAtualizada.getEndTime() != null) {
            atividadeExistente.setEndTime(atividadeAtualizada.getEndTime());
        }

        // Caso os campos de tempo sejam alterados, deve-se recalcular a duração
        if (atividadeExistente.getStartTime() != null && atividadeExistente.getEndTime() != null) {
            long minutes = java.time.Duration.between(atividadeExistente.getStartTime(), atividadeExistente.getEndTime()).toMinutes();
            atividadeExistente.setDurationMinutes((int) minutes);
        }
        return activityLogRepository.save(atividadeExistente);
    }

    // DELETE
    public void deleteActivityLog(Integer id) {
        // O JpaRepository já tem um metodo pronto para deletar pelo ID
        activityLogRepository.deleteById(id);
    }
}
