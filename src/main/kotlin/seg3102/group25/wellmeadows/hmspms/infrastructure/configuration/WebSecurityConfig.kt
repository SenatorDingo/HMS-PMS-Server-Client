package seg3102.group25.wellmeadows.hmspms.infrastructure.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.services.StaffAuthenticationProvider
import java.util.*

@Configuration
class WebSecurityConfig{

    val superRole = arrayOf("ADMIN")

    val admitPatientRole = AccessLevels.AdmitPatient.staffType.map { it.name }.toTypedArray()
    val admitPatientRequestListRole = AccessLevels.AdmitPatientRequestList.staffType.map { it.name }.toTypedArray()
    val consultPatientFileRole = AccessLevels.ConsultPatientFile.staffType.map { it.name }.toTypedArray()
    val dischargePatientRole = AccessLevels.DischargePatient.staffType.map { it.name }.toTypedArray()
    val prescribeMedicationRole = AccessLevels.PrescribeMedication.staffType.map { it.name }.toTypedArray()
    val registerPatientRole = AccessLevels.RegisterPatient.staffType.map { it.name }.toTypedArray()
    val registerStaffRole = AccessLevels.RegisterStaff.staffType.map { it.name }.toTypedArray()
    val requestPatientAdmissionRole = AccessLevels.RequestPatientAdmission.staffType.map { it.name }.toTypedArray()
    val updatePatientFileRole = AccessLevels.UpdatePatientFile.staffType.map { it.name }.toTypedArray()
    val visualizeDivisionRole = AccessLevels.VisualizeDivision.staffType.map { it.name }.toTypedArray()

    @Bean
    @Order(1)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests{ requests -> requests
                .anyRequest()
                .authenticated()
            }
            .formLogin{form -> form
                .loginPage("/login")
                .permitAll()
            }
            .logout { logout -> logout
                .permitAll()
            }
            .userDetailsService(userDetailService())

        return http.build()
    }


    @Bean
    @Order(2)
    fun filterStaffChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { requests -> requests
                .requestMatchers("/actions/admit-patient").hasAnyRole(*superRole, *admitPatientRole)
                .requestMatchers("/actions/admit-patient-request-list").hasAnyRole(*superRole, *admitPatientRequestListRole)
                .requestMatchers("/actions/consult-patient").hasAnyRole(*superRole, *consultPatientFileRole)
                .requestMatchers("/actions/discharge-patient").hasAnyRole(*superRole, *dischargePatientRole)
                .requestMatchers("/actions/prescribe-medication").hasAnyRole(*superRole, *prescribeMedicationRole)
                .requestMatchers("/actions/register-patient").hasAnyRole(*superRole, *registerPatientRole)
                .requestMatchers("/actions/register-staff").hasAnyRole(*superRole, *registerStaffRole)
                .requestMatchers("/actions/request-patient-admission").hasAnyRole(*superRole, *requestPatientAdmissionRole)
                .requestMatchers("/actions/update-patient-file").hasAnyRole(*superRole, *updatePatientFileRole)
                .requestMatchers("/actions/visualize-division").hasAnyRole(*superRole, *visualizeDivisionRole)
            }
            .authenticationProvider(StaffAuthenticationProvider())
        return  http.build()
    }

    @Bean
    fun userDetailService(): UserDetailsService {
        val admin: UserDetails = User
            .withUsername("admin")
            .password(passwordEncoder()?.encode("admin"))
            .roles("ADMIN")
            .build()

        return InMemoryUserDetailsManager(admin)
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring()
                .requestMatchers(
                    "/resources/**",
                    "/static/**",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/vendor/**",
                    "/fonts/**",
                    "/error"
                )
        }
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}