package ftc.rogue.blacksmith.util

import ftc.rogue.blacksmith.units.AngleUnit
import ftc.rogue.blacksmith.units.DistanceUnit
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.math.PI

// (Generated by ChatGPT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MUTest {
    @Nested
    inner class ToInTest {
        @Test
        fun `toIn converts from cm to inches`() {
            assertEquals(10.0, 25.4.toIn(DistanceUnit.CM), 1e-6)
        }

        @Test
        fun `toIn converts from inches to inches`() {
            assertEquals(10.0, 10.toIn(DistanceUnit.INCHES), 1e-6)
        }
    }

    @Nested
    inner class ToCmTest {
        @Test
        fun `toCm converts from cm to cm`() {
            assertEquals(10.0, 10.toCm(DistanceUnit.CM), 1e-6)
        }

        @Test
        fun `toCm converts from inches to cm`() {
            assertEquals(25.4, 10.toCm(DistanceUnit.INCHES), 1e-6)
        }
    }

    @Nested
    inner class ToRadTest {
        @Test
        fun `toRad converts from degrees to radians`() {
            assertEquals(PI, 180.toRad(AngleUnit.DEGREES), 1e-6)
        }

        @Test
        fun `toRad converts from radians to radians`() {
            assertEquals(180.0, 180.toRad(AngleUnit.RADIANS), 1e-6)
        }
    }

    @Nested
    inner class DoubleZeroIfNaNTest {
        @Test
        fun `Double_zeroIfNan returns 0 if the input is NaN`() {
            assertEquals(0.0, Double.NaN.zeroIfNaN(), 1e-6)
        }

        @Test
        fun `Double_zeroIfNan returns the input if the input is not NaN`() {
            assertEquals(1.0, 1.0.zeroIfNaN(), 1e-6)
        }
    }

    @Nested
    inner class FloatZeroIfNaNTest {
        @Test
        fun `Float_zeroIfNan returns 0 if the input is NaN`() {
            assertEquals(0.0, Float.NaN.zeroIfNaN().toDouble(), 1e-6)
        }

        @Test
        fun `Float_zeroIfNan returns the input if the input is not NaN`() {
            assertEquals(1.0, 1.0f.zeroIfNaN().toDouble(), 1e-6)
        }
    }

    @Nested
    inner class IsInRangeTest {
        @Test
        fun `isInRange returns true if the number is within the given range`() {
            assertEquals(true, 5.isInRange(1, 10))
        }

        @Test
        fun `isInRange returns false if the number is not within the given range`() {
            assertEquals(false, 20.isInRange(1, 10))
        }
    }

    @Nested
    inner class ClampTest {
        @Test
        fun `clamp returns the number if it is within the given range`() {
            assertEquals(5.0, 5.clamp(1, 10))
        }

        @Test
        fun `clamp returns the min value if the number is less than the min value`() {
            assertEquals(1.0, 0.clamp(1, 10))
        }

        @Test
        fun `clamp returns the max value if the number is greater than the max value`() {
            assertEquals(10.0, 20.clamp(1, 10))
        }
    }

    @Nested
    inner class AvgTest {
        @Test
        fun `avg returns the average of the given numbers`() {
            assertEquals(5.5, avg(1, 10))
            assertEquals(5.66666666, avg(1, 10, 6), 1e-6)
        }
    }

    @Nested
    inner class MaxMagnitudeTest {
        @Test
        fun `maxMagnitude returns the max magnitude of the given numbers`() {
            assertEquals(-10, maxByMagnitude(-10, -5, 5, 10))
            assertEquals(-15, maxByMagnitude(-10, -5, -15))
        }

        @Test
        fun `maxMagnitude returns 0 if no numbers are given`() {
            assertEquals(0.0, maxByMagnitude())
        }
    }

    @Nested
    inner class WithDeadzoneTest {
        @Test
        fun `withDeadzone returns the origin if the difference between x and the origin is greater than the deadzone`() {
            assertEquals(0.0, 1.withDeadzone(2))
        }

        @Test
        fun `withDeadzone returns x if the difference between x and the origin is less than or equal to the deadzone`() {
            assertEquals(1.0, 1.withDeadzone( 0))
        }

        @Test
        fun `withDeadzone works with a non-zero origin`() {
            assertEquals(1.0, 1.withDeadzone(2, 1))
            assertEquals(2.0, 3.withDeadzone(2, 2))
        }
    }
}
