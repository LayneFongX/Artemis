package com.farid.artemis.domain.biz.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EliqEquipmentBO {

    private String id;

    private String label;

    private String family;

    private String creationDate;

    private Object metaData;

    private List<String> tags;

    private Boolean controllable;

    private Boolean monitored;

    private String protocol;

    private ElectricalDefinition electricalDefinition;

    private ControlConfiguration controlConfiguration;

    private ChargeConfiguration chargeConfiguration;

    private Status status;

    private ControlFunctionAffectation controlFunctionAffectation;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ElectricalDefinition {

        private Integer statedPower;

        private Integer onDelay;

        private Integer offDelay;

        private Integer numberOfPole;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ControlConfiguration {

        private Integer fallback_setpoint;

        private String fallback_state;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChargeConfiguration {

        private Integer minChargeSetpoint;

        private Integer maxChargeSetpoint;

        private Integer minDischargeSetpoint;

        private Integer maxDischargeSetpoint;

        private Integer minStateOfCharge;

        private Integer maxStateOfCharge;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Status {

        private Integer setpointStatus;

        private String loadStatusLastChange;

        private String loadStatus;

        private String operatingStatus;

        private String faultStatus;

        private String protocolSpecificStatus;

        private Integer stateOfCharge;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ControlFunctionAffectation {

        private String functionalControlMode;

        private String currentControlMode;

        private Scm scm;

        private Pcm pcm;

        private Schedule schedule;

        private Manual manual;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Scm {

            private Boolean scmSupported;

            private Integer scmPriority;

            private List<Schedule> complementarySchedule;

            private Integer ecoStart;

            private Integer ecoStop;

            private String derogationMode;

            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Schedule {

                private String label;

                private List<String> daysOfTheWeek;

                private List<Hour> hours;

                @Data
                @NoArgsConstructor
                @AllArgsConstructor
                public static class Hour {

                    private Integer onHour;

                    private Integer onMinute;

                    private Integer offHour;

                    private Integer offMinute;
                }
            }
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Pcm {

            private Boolean pcmSupported;

            private Boolean pcmMode;

            private Integer pcmPriority;

        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Schedule {

            private Boolean scheduleSupported;

            private List<SchedulingRule> schedulingRules;

            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class SchedulingRule {

                private String label;

                private List<String> daysOfTheWeek;

                private List<Hour> hours;

                @Data
                @NoArgsConstructor
                @AllArgsConstructor
                public static class Hour {

                    private Integer onHour;

                    private Integer onMinute;

                    private Integer offHour;

                    private Integer offMinute;
                }
            }
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Manual {

            private Boolean manualSupported;
        }
    }
}
