#include <stdio.h>

#include <stdlib.h>
static int __RVC_state = 0; 



int __RVC_SeatBelt_safe = 0;
int __RVC_SeatBelt_unsafe = 0;

void
__RVC_SeatBelt_reset(void)
{
  __RVC_state = 0;
 }

static int __RVC_SEATBELT_SEATBELTREMOVED[] = {-1,0, };
static int __RVC_SEATBELT_SEATBELTATTACHED[] = {1, -1,};

void
__RVC_SeatBelt_seatBeltRemoved()
{
{fprintf(stderr, "Seat belt removed.\n");}
__RVC_state = __RVC_SEATBELT_SEATBELTREMOVED[__RVC_state];
  __RVC_SeatBelt_safe = __RVC_state == 1;
  __RVC_SeatBelt_unsafe = __RVC_state == 0;
if(__RVC_SeatBelt_safe)
{
{
      fprintf(stderr, "set max speed to user input.\n");
  }}
if(__RVC_SeatBelt_unsafe)
{
{
      fprintf(stderr, "set max speed to 10 mph.\n");
  }}
}

void
__RVC_SeatBelt_seatBeltAttached()
{
{fprintf(stderr, "Seat belt attached.\n");}
__RVC_state = __RVC_SEATBELT_SEATBELTATTACHED[__RVC_state];
  __RVC_SeatBelt_safe = __RVC_state == 1;
  __RVC_SeatBelt_unsafe = __RVC_state == 0;
if(__RVC_SeatBelt_safe)
{
{
      fprintf(stderr, "set max speed to user input.\n");
  }}
if(__RVC_SeatBelt_unsafe)
{
{
      fprintf(stderr, "set max speed to 10 mph.\n");
  }}
}


