from torch import Tensor as Tensor
from typing import Optional

def grid_sample(img: Tensor, absolute_grid: Tensor, mode: str = ..., align_corners: Optional[bool] = ...): ...
def make_coords_grid(batch_size: int, h: int, w: int, device: str = ...): ...
def upsample_flow(flow, up_mask: Optional[Tensor] = ..., factor: int = ...): ...