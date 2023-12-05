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
import org.springframework.http.HttpMethod
import seg3102.group25.wellmeadows.hmspms.domain.security.valueObjects.AccessLevels
import seg3102.group25.wellmeadows.hmspms.infrastructure.web.services.StaffAuthenticationProvider
import java.util.*

@Configuration
class WebSecurityConfig{

    @get:Bean
    val superRole: Array<String> = arrayOf("ADMIN")

    val admitPatientRole: Array<String> = AccessLevels.AdmitPatient.staffType.map { it.name }.toTypedArray()
    val admitPatientRequestListRole: Array<String> = AccessLevels.AdmitPatientRequestList.staffType.map { it.name }.toTypedArray()
    val consultPatientFileRole: Array<String> = AccessLevels.ConsultPatientFile.staffType.map { it.name }.toTypedArray()
    val dischargePatientRole: Array<String> = AccessLevels.DischargePatient.staffType.map { it.name }.toTypedArray()
    val prescribeMedicationRole: Array<String> = AccessLevels.PrescribeMedication.staffType.map { it.name }.toTypedArray()
    val registerPatientRole: Array<String> = AccessLevels.RegisterPatient.staffType.map { it.name }.toTypedArray()
    val registerStaffRole: Array<String> = AccessLevels.RegisterStaff.staffType.map { it.name }.toTypedArray()
    val requestPatientAdmissionRole: Array<String> = AccessLevels.RequestPatientAdmission.staffType.map { it.name }.toTypedArray()
    val updatePatientFileRole: Array<String> = AccessLevels.UpdatePatientFile.staffType.map { it.name }.toTypedArray()
    val visualizeDivisionRole: Array<String> = AccessLevels.VisualizeDivision.staffType.map { it.name }.toTypedArray()

    @Bean
    @Order(1)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests{ requests -> requests
                .requestMatchers("/").authenticated()
                .anyRequest().authenticated()
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
    fun filterAdminChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests{ requests -> requests
                .requestMatchers("/admin/**").hasAnyAuthority(*superRole)
                .requestMatchers("/swagger-ui/**").hasAnyAuthority(*superRole)
                .anyRequest().authenticated()
           }

        return http.build()
    }


    @Bean
    @Order(3)
    fun filterStaffChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { requests -> requests
                .requestMatchers("/actions/admit-patient/**").hasAnyAuthority(*admitPatientRole)
                .requestMatchers("/actions/admit-patient-request-list/**").hasAnyAuthority(*admitPatientRequestListRole)
                .requestMatchers("/actions/consult-patient/**").hasAnyAuthority(*consultPatientFileRole)
                .requestMatchers("/actions/discharge-patient/**").hasAnyAuthority(*dischargePatientRole)
                .requestMatchers("/actions/prescribe-medication/**").hasAnyAuthority(*prescribeMedicationRole)
                .requestMatchers("/actions/register-patient/**").hasAnyAuthority(*registerPatientRole)
                .requestMatchers("/actions/register-staff/**").hasAnyAuthority(*registerStaffRole)
                .requestMatchers("/actions/request-patient-admission/**").hasAnyAuthority(*requestPatientAdmissionRole)
                .requestMatchers("/actions/update-patient-file/**").hasAnyAuthority(*updatePatientFileRole)
                .requestMatchers("/actions/visualize-division/**").hasAnyAuthority(*visualizeDivisionRole)
                .anyRequest().authenticated()
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
                    "/error/**",
                    "/test/**",
                    //"/admin/**", //TODO: REMOVE THIS LINE - This is added for OpenAPI ONLY DEV
                    //"/actions/**" //TODO: REMOVE THIS LINE - This is added for OpenAPI ONLY DEV
                )
        }
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}